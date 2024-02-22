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

package io.github.michaelmior.bloomfilter.impl;

import io.github.michaelmior.bloomfilter.AbstractBloomFilter;
import io.github.michaelmior.bloomfilter.core.BitArray;
import io.github.michaelmior.bloomfilter.core.RoaringBitSetArray;

/**
 * A Roaring Bitmap bloom filter.
 * 
 * @author sangupta
 * @since 1.0
 * 
 * @param <T> the type of object to be stored in the filter
 */
public class RoaringBloomFilter<T> extends AbstractBloomFilter<T> {
	
	/**
	 * Constructor
	 * 
	 * @param n
	 *            the number of elements expected to be inserted in the bloom
	 *            filter
	 * 
	 * @param fpp
	 *            the expected max false positivity rate
	 */
	public RoaringBloomFilter(int n, double fpp) {
		super(n, fpp);
	}

	/**
	 * Used a normal {@link JavaBitSetArray}.
	 * 
	 */
	@Override
	protected BitArray createBitArray(int numBits) {
		return new RoaringBitSetArray(numBits);
	}
	
}
