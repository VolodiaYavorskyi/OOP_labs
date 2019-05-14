package lab4;

import java.util.Iterator;

public class MyIterator<T> implements Iterator<T> {
	LinkedList<T> list = new LinkedList<T>();
	int index;
	
	MyIterator(LinkedList<T> list) {
		this.list = list;
		index = 0;
	}
	
	@Override
	public boolean hasNext() {
		return (index < list.size());
	}

	@Override
	public T next() {
		return list.get(index++);
	}
	
	@Override
	public void remove() {
		list.remove(--index);
	}
}