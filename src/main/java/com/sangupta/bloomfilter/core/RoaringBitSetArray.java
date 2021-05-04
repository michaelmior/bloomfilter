/**
 *
 * bloomfilter - Bloom filters for Java
 * Copyright (c) 2014-2015, Sandeep Gupta
 * 
 * http://sangupta.com/projects/bloomfilter
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

package com.sangupta.bloomfilter.core;

import java.io.IOException;
import java.io.Serializable;

import org.roaringbitmap.RoaringBitmap;

/**
 * A {@link BitArray} implementation that uses a Roaring Bitmap.
 * 
 * @author sangupta
 * @since 1.0
 */
public class RoaringBitSetArray implements BitArray, Serializable {
	
	final RoaringBitmap bitmap;
	
	final int size;
	
	public RoaringBitSetArray(int numBits) {
		this.bitmap = new RoaringBitmap();
		this.size = numBits;
	}

	@Override
	public void clear() {
		this.bitmap.clear();
	}

	@Override
	public boolean getBit(int index) {
		return this.bitmap.contains(index);
	}

	@Override
	public boolean setBit(int index) {
		this.bitmap.add(index);
		return true;
	}

	@Override
	public void clearBit(int index) {
		this.bitmap.remove(index);
	}

	@Override
	public boolean setBitIfUnset(int index) {
		if(!this.bitmap.contains(index)) {
			return this.setBit(index);
		}
		
		return false;
	}

	@Override
	public void or(BitArray bitArray) {
		if(bitArray == null) {
			throw new IllegalArgumentException("BitArray to OR with cannot be null");
		}
		
		if (bitArray instanceof RoaringBitSetArray) {
			this.bitmap.or(((RoaringBitSetArray) bitArray).bitmap);
		} else {
			throw new RuntimeException("Operation not yet supported");
		}
	}

	@Override
	public void and(BitArray bitArray) {
		if(bitArray == null) {
			throw new IllegalArgumentException("BitArray to AND with cannot be null");
		}
		
		if(this.size != bitArray.bitSize()) {
			throw new IllegalArgumentException("BitArray to AND with is of different length");
		}
		
		if (bitArray instanceof RoaringBitSetArray) {
			this.bitmap.and(((RoaringBitSetArray) bitArray).bitmap);
		} else {
			throw new RuntimeException("Operation not yet supported");
		}
	}

	@Override
	public int bitSize() {
		return this.size;
	}

	@Override
	public void close() throws IOException {
		// do nothing
	}

}
