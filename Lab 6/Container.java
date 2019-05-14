package lab6;

import java.util.Iterator;

public class Container implements Iterable<Integer> {
	private int[] data;
	private int length;
	
	Container(int length) {
		if (length > 0) {
			this.length = length;
			data = new int[length];
		} else {
			throw new NegativeArraySizeException();
		}
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append("[");	
		for (int i = 0; i < length; i++) {
			result.append(data[i]);
			if (i != length - 1) {
				result.append(", ");
			}
		}
	    result.append("]");
	    
	    return new String(result);
	}
	
	void fill() {
		for (int i = 0; i < length; i++) {
			data[i] = (int) (Math.random() * 5000);
		}
	}
	
	int[] toArray() {
		return data;
	}
	
	int size() {
		return length;
	}
	
	boolean contains(int a) {
		try {
			for (int i = 0; i < length; i++) {
				if (data[i] == a) {
					return true;
				}
			}
		} catch (NullPointerException e) {
			System.out.println("No elements");
		}
		
		return false;
	}
	
	boolean containsAll(Container container) {
		boolean found;
		
		for (int i = 0; i < container.size(); i++) {
			found = false;
			
			for (int j = 0; j < this.size(); j++) {
				if (container.data[i] == this.data[j]) {
					found = true;
					break;
				}
			}
			
			if (!found) {
				return false;
			}
		}
		
		return true;
	}
	
	int get(int i) {
		try {
			return data[i];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Wrong index");
		}
		
		return 0;
	}
	
	void set(int i, int value) {
		try {
			data[i] = value;
			return;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Wrong index");
		}
		
		return;
	}
	
	int minimum() {
		int min = 999999;
		
		for (int i = 0; i < length; i++) {
			if (data[i] < min) {
				min = data[i];
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread() + " interrupted");
			}
		}
		
		return min;
	}
	
	int maximum() {
		int max = -1;
		
		for (int i = 0; i < length; i++) {
			if (data[i] > max) {
				max = data[i];
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread() + " interrupted");
			}
		}
		
		return max;
	}
	
	long sum() {
		long result = 0;
		
		for (int i = 0; i < length; i++) {
			result += data[i];
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread() + " interrupted");
			}
		}
		
		return result;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new MyIterator(data, length);
	}
}