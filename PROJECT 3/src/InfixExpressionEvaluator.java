/****************************
 * Christine Do
 * 03/14/19
 * Project 3
 * InfixExpressionEvaluator.java
 * Description: This class takes in a String representation of a mathematical infix expression and solves 
 * it using the Stack data structure
 * CMSC 256- Spring 2019
 */
import java.util.ArrayList;
public class InfixExpressionEvaluator<T> extends MyStack<T>{
		/**************************************************************
		 * puts String infix into an array, infixArray
		 * puts values of that array into ArrayLists depending if it's
		 * a value or operator
		 * pushes or pops elements off the valuesStack or operatorStack
		 * depending on the condition
		 * returns the final value pushed onto the stack
		 *************************************************************/
	    public static double evaluateInfix(String infix, int[] values){
	    	//Note: possible ending garbage indexes
	    	String[] postfixStr = infixToPostfix(infix);
	    	
	    	//stores operands
	    	MyStack<String> stack = new MyStack<String>();
	    	
	    	int i = 0;
	    	while(!(postfixStr[i].equals(null)) && i < postfixStr.length) {
	    		//if a number, push onto stack
	    		if(isNumeric(postfixStr[i])) {
	    			stack.push(postfixStr[i]);
	    		    i++;
	    		    if(i >= postfixStr.length) break;
	    		}
	    		//else if a variable,get its value and push onto stack
	    		else if(isVariable(postfixStr[i])) {
	    			double value = returnVariableValue(postfixStr[i], values);
	    			stack.push(Double.toString(value));
	    		    i++;
	    		    if(i >= postfixStr.length) break;
	    		}
	    		//if operator, pop two values off stack and calculate it with the operator
	    		//push result onto stack
	    		if(isOperator(postfixStr[i])) {
	    			double b = Double.parseDouble(stack.pop());
	    			double a = Double.parseDouble(stack.pop());
	    			String op = postfixStr[i]; 
	    			i++;
	    			double result = calculate(a,op,b);
	    			stack.push(Double.toString(result));
	    			if(i >= postfixStr.length) break;
	    		}
	    	}//end while
	    	
	    	//this is the final value
			return Double.parseDouble(stack.pop());
			
		
	    }//end evaluateInfix
	    
	    public static String[] infixToPostfix(String infix) {
	    	//contains the expression in inix form
			String[] infixArray = infix.split(" "); 
			
			//contains the expression in postfix form w/o parenthesis
			String[] postfixArray = new String[infixArray.length]; 
			
			//stores operators
			MyStack<String> stack = new MyStack<String>();
			
			//check if parenthesis are even
			int open = 0; 
			int close = 0;
			
			//if expression has ( or ), make sure to delete the space for that string in postfixArray
			int nullCount = 0;
			
			for(int i = 0; i < infixArray.length; i++) {
				if(infixArray[i].contains("(")) {
					open++;
					nullCount++;
				}
				else if(infixArray[i].contains(")")){
					close++;
					nullCount++;
				}
			}
			//number of opening parenthesis don't match number of closing parenthesis
			if(open != close) {
				throw new IllegalStateException();
			}
			
			int i = 0; 
			int j = 0;
			
			while(i < infixArray.length) {
				//if an operand is found, add it to postfix array
				if((isNumeric(infixArray[i]) || isVariable(infixArray[i]))) {
						postfixArray[j] = infixArray[i];
						i++;
						j++;
					//break out of while loop so program doesn't crash when checking infixArray[i]
					//for i > last index
					if(i > infixArray.length - 1) break;
				}
				
				//if opening parenthesis found, push onto stack
				if(infixArray[i].contains("(")) {
					stack.push(infixArray[i]);
					i++;
				}
				
				//if a closing parenthesis is found
				if(infixArray[i].contains(")")) {
					//while stack is not empty and top of stack is not an opening parenthesis
					while(!stack.isEmpty() && (!(stack.peek().contains("(")))) {
						//for cases where there are more than 1 consecutive ) or (
						//pop them so it doesn't get added to postfix array
						while(stack.peek().contains(")")){
							stack.pop();
						}
						while(stack.peek().contains("(")){
							stack.pop();
						}
						//pop stack and add its value to postfix array
						postfixArray[j] = stack.pop();
						i++;
						j++;
					}//end while
					//pop left parenthesis and discard
					stack.pop();
					
					//break out of while loop so program doesn't crash when checking infixArray[i]
					//for i > last index
					if(i > infixArray.length - 1) break;
				}//end if
				
				//if an operator is found
				if(isOperator(infixArray[i])) {
					//if stack is empty or top of stack is an opening parenthesis
					if(stack.isEmpty() || stack.peek().contains("(")) {
						//push operator onto stack
						stack.push(infixArray[i]);
						i++;
					}
					
					else {
						/* stack NOT empty &&
						 * top of stack is NOT an open parenthesis &&
						 * operator precedence of infixArray[i] <= operator precedence of operator on top of stack
						 */
						while(!stack.isEmpty() && (!(stack.peek().contains("(")))
							&& priorityOfOperator(infixArray[i]) <= priorityOfOperator(stack.peek())) {
							//pop stack and add value to postfixArray
							postfixArray[j] = stack.pop();
							j++;
						}//end while
						
						//push operator onto stack
						stack.push(infixArray[i]);
						i++;
					}
					
				}//end if
			}//end while
			
			//while stack not empty, pop and add values to postfixArray
			while(!stack.isEmpty()) {
				postfixArray[j] = stack.pop();
				j++;
			}
			
			//edge case for possible trailing garbage null values
			if(nullCount > 0) {
				String[] temp = new String[postfixArray.length-nullCount];
				//new array without trailing null values 
				for(int z = 0; z < temp.length; z++) {
					temp[z] = postfixArray[z];
				}
				postfixArray = temp;
			}
			
			return postfixArray;
	    }
	    
	    /******************************************************************
	     * evaluates expression based on the operator passed
	     *****************************************************************/
	    private static double calculate(double left, String op, double right){
	        switch (op){
	            case "+": return left + right;
	            case "-": return left - right;
	            case "*": return left * right;
	            case "/": return left / right;
	            case "^": return Math.pow(left, right);
	        }
	        return 0;
	    }
	    
	    /********************************************
	     * returns true if string is numeric
	     *******************************************/
	    private static boolean isNumeric(String str) {
	    	try {
	    		Double.parseDouble(str);
	    		return true;
	    	}
	    	catch(NumberFormatException e) {
	    		return false;
	    	}
	    }
	    
	    /************************************************
	     * returns true if string is a letter variable
	     ***********************************************/
	    private static boolean isVariable(String str) {
	    	switch(str) {
	    	case "a": case "A": case "b": case "B": case "c": case "C":
	    	case "d": case "D": case "e": case "E": case "f": case "F":
	    		return true;
	    	default: return false;
	    	}
	    }
	    
	    /*************************************************
	     * returns an int value of an operator's priority
	     ************************************************/
	    private static int priorityOfOperator(String op){
	        switch (op){
	            case "+": case "-": return 1;
	            case "*": case "/": return 2;
	            case "^": return 3;
	        }
	        return -1;
	    }
	    
	    /*******************************************
	     * parses numeric string to a double
	     ******************************************/
	    private static double parseValue(String str) {
			double value = Double.parseDouble(str);
			return value;
		} 
	    
	    /*****************************************************
	     * returns true if the String contains an operator
	     ****************************************************/
	    private static boolean isOperator(String operatorStr) {
			 if ((operatorStr.contains("^")) || (operatorStr.contains("*")) || 
					(operatorStr.contains("/")) || (operatorStr.contains("+")) || 
					(operatorStr.contains("-")) || (operatorStr.contains("("))
					|| (operatorStr.contains(")"))) {
				return true;
			}
			
			return false;
		}
	    
	    /*************************************************************
	     * returns the double representation of a variable using the
	     * array of values passed to InfixExpressionEvaluator
	     ************************************************************/
	    private static double returnVariableValue(String str, int[] nums) {
			switch(str) {
			case "a": case "A": return nums[0]; 
			case "b": case "B": return nums[1]; 
			case "c": case "C": return nums[2]; 
			case "d": case "D": return nums[3]; 
			case "e": case "E": return nums[4]; 
			case "f": case "F": return nums[5]; 
			default: return 0;
			}
		}
}//end class
