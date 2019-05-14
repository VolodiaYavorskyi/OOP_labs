package lab4;

import java.io.Serializable;
import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>, Serializable {
	private Node head;

	public class Node implements Serializable {
		private T data;
		private Node next;
		
		Node (T data) {
			this.data = data;
		}
	}
	
	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}
	
	public void showList() {
		Node current = head;
		
		while (current != null) {
			System.out.print(current.data + " ");
			current = current.next;
		}
		
		System.out.println();
	}
	
	public void insert(T data) {
		Node node = new Node(data);
		
		if (head == null) {
			head = node;
		} else {
			Node last = head;
			
			while (last.next != null) {
				last = last.next;
			}
			
			last.next = node;
		}
	}
	
	public void insert(int index, T data) {
		if (index < 0) {
			System.err.println("Wrong index");
			
			return;
		}
		
		Node node = new Node(data);
		
		if (index == 0) {
			node.next = head;
			head = node;
			
			return;
		}
		
		Node current = head;
		
		for (int i = 1; i < index; i++) {
			if (current.next == null) {
				System.err.println("No enough elements");
				
				return;
			} else {
				current = current.next;
			}
		}
		
		node.next = current.next;
		current.next = node;
	}
	
	public void remove() {		
		if (head != null) {
			if (head.next == null) {
				head = null;
			} else {
				Node previous = head;
				Node current = head.next;
							
				while (current.next != null) {
					previous = current;
					current = current.next;
				}
				
				previous.next = null;
			}		
		}
	}
	
	public void remove(int index) {
		if (index == 0) {
			head = head.next;
			
			return;
		}
		
		Node previous = head;
		Node current = head.next;
		
		for (int i = 1; i < index; i++) {
			if (current.next == null) {
				System.err.println("No enought elements");
				
				return;
			} else {
				previous = current;
				current = current.next;
			}
		}
		
		previous.next = current.next;
	}
	
	public void clear() {
		head = null;
	}
	
	public Object[] toArray() {
		Object[] Arr;
		int i = 0;
		Node current = head;
		
		while (current != null) {
			current = current.next;
			i++;
		}
		
		Arr = new Object[i];
		current = head;
		
		for (int j = 0; j < i; j++) {
			Arr[j] = current.data;
			current = current.next;
		}
		
		return Arr;
	}
	
	public String toString() {
		String result = "[ ";
		
		Node current = head;
		
		while (current.next.next != null) {
			result += current.data + ", ";
			current = current.next;
		}
		
		result += current.next.data + " ]";
		
		return result;
	}
	
	public boolean isEmpty() {
		return (head == null);
	}
	
	public T get(int index) {
		if (index < 0) {
			System.err.println("Wrong index");
			
			return null;
		}
		
		Node current = head;
		
		for (int i = 0; i < index; i++) {
			if (current == null) {
				System.err.println("Not enough elements");
				
				return null;
			} else {
				current = current.next;
			}
		}
		
		return current.data;
	}

	public int size() {
		int counter = 0;
		Node current = head;
		
		while (current != null) {
			current = current.next;
			counter++;
		}
		
		return counter;
	}
	
	@Override
	public Iterator<T> iterator() {
		return (new MyIterator<T>(this));
	}
}