import bridges.connect.Bridges;
import bridges.validation.RateLimitException;
import java.util.EmptyStackException;
import bridges.base.SLelement;
/****************************
 * Christine Do
 * 03/14/19
 * Project 3
 * MyBridgesStack.java
 * Description: This class creates a stack data structure using the BRIDGES SLelement class
 * CMSC 256- Spring 2019
 */
public class MyBridgesStack<T> implements StackInterface<T> {
	
	private SLelement<T> top;
	private SLelement<T> next;
	
	
	@Override
	public void push(T newEntry) {	
		SLelement<T> newElement = new SLelement<T>(newEntry);
		newElement.setNext(top);
		top = newElement;
	}

	@Override
	public T pop() {
		if (top != null) {
			T toReturn = top.getValue();
			top = top.getNext();
			return toReturn;
		}
		else {
			throw new EmptyStackException();
		}
	}

	@Override
	public T peek() {
		if(top != null) {
			return top.getValue();
		}
		else {
			throw new EmptyStackException();
		}
	}

	@Override
	public boolean isEmpty() {
		if(top != null) {
			return false;
		}
		return true;
	}

	@Override
	public void clear() {
		top = null;
		
	}
	
	public void visualize() {
		Bridges bridges = new Bridges(1, "docm", "1093327499414");
		bridges.setTitle("Stack");
		
		while(top != null) {
			top.setLabel((String) top.getValue());
			top.getElementRepresentation();
			if(top.getNext() != null) {
				next = top.getNext();
				next.setLabel((String) next.getValue());
				next.getElementRepresentation();
			}
			top = top.getNext();
		}
		
		bridges.setDataStructure(top);
		
	}

}
