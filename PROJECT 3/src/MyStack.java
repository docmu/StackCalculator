/****************************
 * Christine Do
 * 03/14/19
 * Project 3
 * MyStack.java
 * Description: This creates the Stack data structure using a private inner Node class 
 * CMSC 256- Spring 2019
 */
import java.util.EmptyStackException;

public class MyStack<T> implements StackInterface<T>{
	
	private class Node<T> {
		private T data;
		private Node<T> nextNode;
		
		public Node(T dataPortion) {
			this(dataPortion, null);
		}
		public Node(T dataPortion, Node<T> next) {
			data = dataPortion;
			nextNode = next;
		}
		public T getData() {
			return data;
		}
		public void setData(T newData) {
			data = newData;
		}
		public Node<T> getNextNode(){
			return nextNode;
		}
		public void setNextNode(Node<T> next) {
			nextNode = next;
		}
	}//end Node class
	
	private Node<T> top;
	
	@Override
	public void push(T newEntry) {
		Node<T> newNode = new Node<T>(newEntry);
		newNode.setNextNode(top);
		top = newNode;
		
	}
	@Override
	public T pop() {
		if (top != null) {
			T toReturn = top.getData();
			top = top.getNextNode();
			return toReturn;
		}
		else {
			throw new EmptyStackException();
		}
	}

	@Override
	public T peek() {
		if(top != null) {
			return top.getData();
		}
		else {
			throw new EmptyStackException();
		}
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public void clear() {
		top = null;
	}
	
	public String toString() {
		while(!isEmpty()) {
			System.out.print("TOP: ");
			return pop() + " ";
		}
		return null;
	}

}//end MyStack<T> class
