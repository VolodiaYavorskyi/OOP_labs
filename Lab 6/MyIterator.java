package lab6;

import java.util.Iterator;

public class MyIterator implements Iterator<Integer> {
	private int[] data;
	private int index = 0;
	private int length;
	
	MyIterator(int[] data, int length) {
		this.data = data;
		this.length = length;
	}
	
	@Override
	public boolean hasNext() {
		return index < length;
	}

	@Override
	public Integer next() {
		return data[index++];
	}
}