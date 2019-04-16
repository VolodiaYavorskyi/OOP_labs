package lab2;

import java.util.Iterator;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Container implements Iterable<String>, Serializable {
	private String[] data;
	private int length;
	private int index = 0;
	
	Container(int length) {
		if (length > 0) {
			this.length = length;
			data = new String[length];
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
	
	void add(String string) {
		if (index < length) {
			data[index++] = string;
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
	
	void clear() {
		for(int i = 0; i < length; i++) {
			data[i] = null;
		}
		
		index = 0;
	}
	
	boolean remove(String string) {
		for (int i = 0; i < length; i++) {
			if (data[i].equals(string)) {
				for (int j = i; j < length - 1; j++) {
					data[j] = data[j + 1];
				}
				
				data[length - 1] = null;
				index--;
				
				return true;
			}
		}
		
		return false;
	}
	
	Object[] toArray() {
		return data;
	}
	
	int size() {
		return index;
	}
	
	boolean contains(String string) {
		try {
			for (int i = 0; i < length; i++) {
				if (data[i].equals(string)) {
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
				if (container.data[i].equals(this.data[j])) {
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
	
	boolean serialize(String filename) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
			
			return true;
		} catch (Exception e) {
			System.out.println("Error");
			
			return false;
		}
	}
	
	boolean deserialize(String filename) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			
			Container temp = (Container) ois.readObject();
			ois.close();
			
			this.data = temp.data;
			this.length = temp.length;
			this.index = temp.index;
			
			return true;
		} catch (Exception e) {
			System.out.println("Error");
			
			return false;
		}
	}
	
	String get(int i) {
		try {
			return data[i];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Wrong index");
		}
		
		return null;
	}
	
	void set(int i, String str) {
		try {
			data[i] = str;
			return;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Wrong index");
		}
		
		return;
	}
	
	@Override
	public Iterator<String> iterator() {
		return new MyIterator(data, length);
	}
}