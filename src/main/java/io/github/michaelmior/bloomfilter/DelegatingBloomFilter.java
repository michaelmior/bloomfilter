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

import java.nio.charset.Charset;
import java.util.Collection;

import io.github.michaelmior.bloomfilter.decompose.Decomposer;

/**
 * @author sangupta
 *
 */
public abstract class DelegatingBloomFilter<T> implements BloomFilter<T> {
	
	protected final BloomFilter<T> originalBloomFilter;
	
	public DelegatingBloomFilter(BloomFilter<T> original) {
		this.originalBloomFilter = original;
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#add(byte[])
	 */
	@Override
	public boolean add(byte[] bytes) {
		return this.originalBloomFilter.add(bytes);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#add(java.lang.Object)
	 */
	@Override
	public boolean add(T value) {
		return this.originalBloomFilter.add(value);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<T> values) {
		return this.originalBloomFilter.addAll(values);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#contains(byte[])
	 */
	@Override
	public boolean contains(byte[] bytes) {
		return this.originalBloomFilter.contains(bytes);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(T value) {
		return this.originalBloomFilter.contains(value);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<T> values) {
		return this.originalBloomFilter.containsAll(values);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#setCharset(java.lang.String)
	 */
	@Override
	public void setCharset(String charsetName) {
		this.originalBloomFilter.setCharset(charsetName);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#setCharset(java.nio.charset.Charset)
	 */
	@Override
	public void setCharset(Charset charset) {
		this.originalBloomFilter.setCharset(charset);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#getObjectDecomposer()
	 */
	@Override
	public Decomposer<T> getObjectDecomposer() {
		return this.originalBloomFilter.getObjectDecomposer();
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#getNumberOfBits()
	 */
	@Override
	public int getNumberOfBits() {
		return this.originalBloomFilter.getNumberOfBits();
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#getFalsePositiveProbability(int)
	 */
	@Override
	public double getFalsePositiveProbability(int numInsertedElements) {
		return this.originalBloomFilter.getFalsePositiveProbability(numInsertedElements);
	}

	/**
	 * @see io.github.michaelmior.bloomfilter.BloomFilter#close()
	 */
	@Override
	public void close() {
		this.originalBloomFilter.close();
	}
}
