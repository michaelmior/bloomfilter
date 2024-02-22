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

package io.github.michaelmior.bloomfilter.hash;

import java.io.Serializable;

import com.sangupta.murmur.Murmur3;

/**
 * A Murmur3 hash function.
 * 
 * @author sangupta
 * @since 1.0
 */
public class Murmur3HashFunction implements HashFunction, Serializable {
	
	private static final long SEED = 0x7f3a21eal;
	
	@Override
	public boolean isSingleValued() {
		return false;
	}

	@Override
	public long hash(byte[] bytes) {
		return Murmur3.hash_x86_32(bytes, 0, SEED);
	}

	@Override
	public long[] hashMultiple(byte[] bytes) {
		return Murmur3.hash_x64_128(bytes, 0, SEED);
	}

}
