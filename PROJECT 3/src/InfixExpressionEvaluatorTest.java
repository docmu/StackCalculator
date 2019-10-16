import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InfixExpressionEvaluatorTest {

	@Test
	public void aSimpleAddTest() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "3 + 3";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 6.0;
		assertEquals("When given the input string 3 + 3 we",expected,actual,.0001);
	}
	
	@Test
	public void aSimpleSubtractTest() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "4 - 5";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = -1.0;
		assertEquals("When given the input string 4 - 5 we",expected,actual,.0001);
	}
	
	@Test
	public void aSimpleMultTest() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "6 * 3";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 18.0;
		assertEquals("When given the input string 6 * 3 we",expected,actual,.0001);
	}
	
	@Test
	public void aSimpleDivideTest() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "7 / 3";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 2.33333;
		assertEquals("When given the input string 7 / 3 we",expected,actual,.0001);
	}
	
	@Test
	public void aSimpleExponentTest() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "2 ^ 8";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 256.0;
		assertEquals("When given the input string 2 ^ 8 we",expected,actual,.0001);
	}
	
	@Test
	public void multipleExpressionTest() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "3 + 3 * 4";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 15.0;
		assertEquals("When given the input string 3 + 3 * 4 we",expected,actual,.0001);
	}
	
	@Test
	public void multipleExpressionTest2() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "9 / 3 - 4";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = -1.0;
		assertEquals("When given the input string 9 / 3 - 4 we",expected,actual,.0001);
	}
	
	@Test
	public void parenthesesTest() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "( 10 - 4 ) * 3";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 18.0;
		assertEquals("When given the input string ( 10 - 4 ) * 3 we",expected,actual,.0001);
	}
	
	@Test
	public void parenthesesTest2() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "11 / ( 2 + 4 )";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 1.8333333;
		assertEquals("When given the input string 11 / ( 2 + 4 ) we",expected,actual,.0001);
	}

	@Test
	public void parenthesesTest3() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "( 2 + 2 )";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 4.0;
		assertEquals("When given the input string ( 2 + 2 ) we",expected,actual,.0001);
	}
	
	@Test
	public void parenthesesTest4Complex() {
		int[] variableValues = {0,0,0,0,0,0};
		String input = "3 ^ ( ( 4 * 4 ) / 8 )";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 9.0;
		assertEquals("When given the input string 3 ^ ( ( 4 * 4 ) / 8 ) we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestSubtract() {
		int[] variableValues = {21,50,0,0,0,0};
		String input = "a - b";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = -29.0;
		assertEquals("When given the input string a - b where a = 21 and b = 50 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestDivide() {
		int[] variableValues = {0,0,10,25,0,0};
		String input = "c / d";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 0.4;
		assertEquals("When given the input string c / d where c = 10 and d = 21 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestExponent() {
		int[] variableValues = {0,0,0,0,3,4};
		String input = "e ^ f";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 81.0;
		assertEquals("When given the input string e ^ f where e = 3 and f = 4 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestCapitalization1() {
		int[] variableValues = {4,2,0,0,0,0};
		String input = "A + a * B";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 12.0;
		assertEquals("When given the input string A + a * B where a = 4 and b = 2 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestCapitalization2() {
		int[] variableValues = {0,0,10,0,6,18};
		String input = "f / E - c";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = -7.0;
		assertEquals("When given the input string f / E - c where c = 10, e = 6 and f = 18 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestParentheses() {
		int[] variableValues = {2,0,18,0,0,0};
		String input = "( C - 4 ) * a";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 28.0;
		assertEquals("When given the input string ( C - 4 ) * a where c = 18 and a = 2 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestParentheses2() {
		int[] variableValues = {0,0,0,0,0,4};
		String input = "( f + f )";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 8.0;
		assertEquals("When given the input string ( f + f ) where f = 4 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestParentheses3() {
		int[] variableValues = {0,4,0,0,4,0};
		String input = "3 ^ ( ( E * E ) / b )";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 81.0;
		assertEquals("When given the input string 3 ^ ( ( E * E ) / b ) where b = 4 and e = 4 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestGarbageVariableData() {
		int[] variableValues = {100,4,32,5,6,8};
		String input = "3 ^ ( ( E * E ) / b )";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 19683.0;
		assertEquals("When given the input string 3 ^ ( ( E * E ) / b ) where b = 4 and e = 6 we",expected,actual,.0001);
	}
	
	@Test
	public void variableTestComplex() {
		int[] variableValues = {2,8,32,156,8,20};
		String input = "( a ^ ( ( b * c ) / ( d - ( e + f ) ) ) + 10 )";
		double actual = InfixExpressionEvaluator.evaluateInfix(input,variableValues);
		double expected = 14.0;
		assertEquals("When given the input string ( a ^ ( ( b * c ) / ( d - ( e + f ) ) ) + 10 ) where a = 2, b = 8, c = 32, d = 156, e = 8 and f = 20 we",expected,actual,.0001);
	}
	
    @Test(expected = IllegalStateException.class)
    // Testing start position greater than end position
    public void parenthesesSingleOpenTest() {
    	int[] variableValues = {0,0,0,0,0,0};
		String input = "( A + 2";
		InfixExpressionEvaluator.evaluateInfix(input,variableValues);
	}
    
    @Test(expected = IllegalStateException.class)
    // Testing start position greater than end position
    public void parenthesesSingleClosedTest() {
    	int[] variableValues = {0,0,0,0,0,0};
		String input = "A + 2 )";
		InfixExpressionEvaluator.evaluateInfix(input,variableValues);
	}
    
    @Test(expected = IllegalStateException.class)
    // Testing start position greater than end position
    public void parenthesesUnbalancedOpenTest() {
    	int[] variableValues = {0,0,0,0,0,0};
		String input = "( ( ( A + 2 ) )";
		InfixExpressionEvaluator.evaluateInfix(input,variableValues);
	}
    
    @Test(expected = IllegalStateException.class)
    // Testing start position greater than end position
    public void parenthesesUnbalancedClosedTest() {
    	int[] variableValues = {0,0,0,0,0,0};
		String input = "( ( ( ( A + 2 ) ) )";
		InfixExpressionEvaluator.evaluateInfix(input,variableValues);
	}
    
    @Test(expected = IllegalStateException.class)
    // Testing start position greater than end position
    public void parenthesesVeryUnbalancedClosedTest() {
    	int[] variableValues = {0,0,0,0,0,0};
		String input = "( A + 2 ) ) ) ) ) ) )";
		InfixExpressionEvaluator.evaluateInfix(input,variableValues);
	}
    
    @Test(expected = IllegalStateException.class)
    // Testing start position greater than end position
    public void parenthesesVeryUnbalancedOpenTest() {
    	int[] variableValues = {0,0,0,0,0,0};
		String input = "( ( ( ( ( ( ( A + 2 )";
		InfixExpressionEvaluator.evaluateInfix(input,variableValues);
	}
}
