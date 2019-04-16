package lab2;

import java.util.Iterator;

public class MyIterator implements Iterator<String> {
	private String[] data;
	private int index = 0;
	private int length;
	
	MyIterator(String[] data, int length) {
		this.data = data;
		this.length = length;
	}
	
	@Override
	public boolean hasNext() {
		return index < length;
	}

	@Override
	public String next() {
		return data[index++];
	}

	@Override
	public void remove() {
		data[--index] = null;
	}
}