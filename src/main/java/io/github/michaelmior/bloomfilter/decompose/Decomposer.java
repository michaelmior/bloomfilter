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

package io.github.michaelmior.bloomfilter.decompose;

/**
 * Contract for implementations that wish to help in decomposing
 * an object to a byte-array so that various hashes can be computed
 * over the same.
 * 
 * @author sangupta
 * @since 1.0
 * 
 * @param <T> the type of object over which this decomposer works
 */
public interface Decomposer<T> {

	/**
	 * Decompose the object into the given {@link ByteSink}
	 * 
	 * @param object
	 *            the object to be decomposed
	 * 
	 * @param sink
	 *            the sink to which the object is decomposed
	 */
	public void decompose(T object, ByteSink sink);

}
