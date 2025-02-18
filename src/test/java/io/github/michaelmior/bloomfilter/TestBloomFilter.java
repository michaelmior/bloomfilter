/**
 *
 * bloomfilter - Bloom filters for Java
 * Copyright (c) 2014-2015, Sandeep Gupta
 * Copyright (c) 2021-2024, Michael Mior
 * 
 * http://github.com/michaelmior/bloomfilter
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package io.github.michaelmior.bloomfilter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.Test;

import io.github.michaelmior.bloomfilter.impl.InMemoryBloomFilter;

/**
 * JUnit tests for {@link InMemoryBloomFilter}
 * 
 * @author sangupta
 *
 */
public class TestBloomFilter {
	
	private static final int MAX = 1000 * 1000;
	
	private static final double FPP = 0.01;

	@Test
	public void testCompatibility() {
		BloomFilter<String> filter1 = new InMemoryBloomFilter<String>(10 * MAX, FPP);
		BloomFilter<String> filter2 = new InMemoryBloomFilter<String>(1 * MAX, FPP);
	    Assert.assertTrue(filter1.isCompatibleWith(filter1));
	    Assert.assertFalse(filter1.isCompatibleWith(filter2));
    }

	@Test
	public void testSubset() {
		InMemoryBloomFilter<String> filter1 = new InMemoryBloomFilter<String>(10 * MAX, FPP);
		InMemoryBloomFilter<String> filter2 = new InMemoryBloomFilter<String>(10 * MAX, FPP);

        filter1.add("foo");

        filter2.add("foo");
        filter2.add("bar");

        Assert.assertTrue(filter1.maybeSubsetOf(filter2));
        Assert.assertTrue(filter2.maybeSupersetOf(filter1));

        Assert.assertFalse(filter2.maybeSubsetOf(filter1));
        Assert.assertFalse(filter1.maybeSupersetOf(filter2));
    }

	@Test
	public void testMerge() {
		InMemoryBloomFilter<String> filter1 = new InMemoryBloomFilter<String>(10 * MAX, FPP);
		InMemoryBloomFilter<String> filter2 = new InMemoryBloomFilter<String>(10 * MAX, FPP);

        filter1.add("foo");
        filter2.add("bar");

        filter1.merge(filter2);

        Assert.assertTrue(filter1.contains("bar"));
    }

	@Test
	public void testIntersect() {
		InMemoryBloomFilter<String> filter1 = new InMemoryBloomFilter<String>(10 * MAX, FPP);
		InMemoryBloomFilter<String> filter2 = new InMemoryBloomFilter<String>(10 * MAX, FPP);

        filter1.add("bar");
        filter2.add("foo");
        filter2.add("bar");

        filter1.intersect(filter2);

        Assert.assertFalse(filter1.contains("foo"));
        Assert.assertTrue(filter1.contains("bar"));
    }
	
	@Test
	public void testDefaultFilter() {
		BloomFilter<String> filter = new InMemoryBloomFilter<String>(10 * MAX, FPP);
		
		// generate two one-million uuid arrays
		List<String> contained = new ArrayList<String>();
		List<String> unused = new ArrayList<String>();
		for(int index = 0; index < MAX; index++) {
			contained.add(UUID.randomUUID().toString());
			unused.add(UUID.randomUUID().toString());
		}
		
		// now add to filter
		for(String uuid : contained) {
			filter.add(uuid);
		}
		
		// now start checking
		for(String uuid : contained) {
			Assert.assertTrue(filter.contains(uuid));
		}
		int fpp = 0;
		for(String uuid : unused) {
			boolean present = filter.contains(uuid);
			if(present) {
				// false positive
				Assert.assertEquals(false, contained.contains(uuid));
				fpp++;
			}
		}
		
		// add another one million more uuids
		List<String> more = new ArrayList<String>();
		for(int index = 0; index < MAX; index++) {
			more.add(UUID.randomUUID().toString());
		}
		for(String uuid : more) {
			filter.add(uuid);
		}
		
		// check again
		for(String uuid : more) {
			Assert.assertTrue(filter.contains(uuid));
		}
		for(int index = 0; index < MAX; index++) {
			String uuid = UUID.randomUUID().toString();
			boolean present = filter.contains(uuid);
			if(present) {
				// false positive
				Assert.assertEquals(false, contained.contains(uuid));
				fpp++;
			}
		}
		System.out.println("False positives found in two millions: " + fpp);
	}

}
