import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyBridgesStackTest {
	MyBridgesStack<String> stack;
	private String n1,n2,n3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		stack = new MyBridgesStack<>();
		n1 = "Able";
		n2 = "Baker";
		n3 = "Charlie";
	}
	
	private void pushAllElements() {
		stack.push(n1);
		stack.push(n2);
		stack.push(n3);
	}

		
	@Test
	public final void visualizeTest() {
		final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
		System.setOut(new PrintStream(myOut));

		pushAllElements();
		stack.visualize();

		final String standardOutput = myOut.toString();
		assertTrue(standardOutput.contains("http://bridges-cs.herokuapp.com/"));
	}
	

	/**
	 * Test push onto an empty stack
	 */
	@Test
	public final void testPush1() {
		stack.push("Rodney Ram");
		assertEquals(stack.peek(), "Rodney Ram");
	}
	
	/**
	 * Test push onto a stack with elements
	 */
	@Test
	public final void testPush2() {
		pushAllElements();
		stack.push("Rodney Ram");
		assertEquals(stack.peek(), "Rodney Ram");
	}
	
	/**
	 * Test push onto a full stack
	 */
	@Test
	public final void testPush3() {
		pushAllElements();
		stack.push("Alan Turing");
		stack.push("Ada Lovelace");
		stack.push("Adm. Grace Hopper");
	}
	

	@Test
	public final void testPush4() {
		pushAllElements();
		stack.push("");
		assertEquals("", stack.peek());
	}

	/**
	 * Test method for pop() an empty stack
	 */
	@Test (expected = EmptyStackException.class)
	public final void testPop1() {
		stack.pop();
	}

	/**
	 * Test method for pop() after push
	 */
	@Test
	public final void testPop2() {
		pushAllElements();
		assertEquals("Charlie", stack.pop());
	}
	
	/**
	 * Test method for pop() after clear()
	 */
	@Test (expected = EmptyStackException.class)
	public final void testPop3() {
		pushAllElements();
		stack.clear();
		stack.pop();
	}
	
	/**
	 * Test peek() before pushing anything
	 */
	@Test (expected = EmptyStackException.class)
	public final void testPeek1() {
		stack.peek();
	}
	
	/**
	 * Test peek() after push
	 */
	@Test
	public final void testPeek2() {
		pushAllElements();
		assertEquals("Charlie", stack.peek());
	}

	/**
	 * Test peek() after push & pop
	 */
	@Test
	public final void testPeek3() {
		pushAllElements();
		stack.pop();
		assertEquals("Baker", stack.peek());
	}
	
	/**
	 * Test peak after clear
	 */
	@Test (expected = EmptyStackException.class)
	public final void testPeek4() {
		pushAllElements();
		stack.clear();
		stack.peek();
	}
	
	/**
	 * Test method for isEmpty() initially
	 */
	@Test
	public final void testIsEmpty1() {
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test method for isEmpty() after push
	 */
	@Test
	public final void testIsEmpty2() {
		pushAllElements();
		assertFalse(stack.isEmpty());
	}
	
	/**
	 * Test method for isEmpty() after push & pop
	 */
	@Test
	public final void testIsEmpty3() {
		pushAllElements();
		stack.pop();
		assertFalse(stack.isEmpty());
	}
	
	/**
	 * Test method for isEmpty() after push & pops
	 */
	@Test
	public final void testIsEmpty4() {
		pushAllElements();
		stack.pop();
		stack.pop();
		stack.pop();
		assertTrue(stack.isEmpty());
	}
	
	/**
	 * Test method for isEmpty() after clear
	 */
	@Test
	public final void testIsEmpty5() {
		pushAllElements();
		stack.clear();
		assertTrue(stack.isEmpty());
	}
	
	/**
	 * Test method for clear() after initialization
	 */
	@Test 
	public final void testClear1() {
		stack.clear();
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test method for clear() after pushing
	 */
	@Test 
	public final void testClear2() {
		pushAllElements();
		stack.clear();
		assertTrue(stack.isEmpty());
	}
}
