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
		
		public enum Operator{ADD, SUBTRACT, MULTIPLY, DIVIDE, POWER, OPENPARENTHESES, CLOSEPARENTHESES}
		/**************************************************************
		 * puts String infix into an array, infixArray
		 * puts values of that array into ArrayLists depending if it's
		 * a value or operator
		 * pushes or pops elements off the valuesStack or operatorStack
		 * depending on the condition
		 * returns the final value pushed onto the stack
		 *************************************************************/
	    public static double evaluateInfix(String infix, int[] values){
			String[] infixArray = infix.split(" ");
			ArrayList<Double> valuesArray = new ArrayList<Double>();
			ArrayList<Operator> operatorsArray = new ArrayList<Operator>();
			
			int openPar = 0;
			int closePar = 0;
			
			for(int i = 0; i < infixArray.length; i++) { 
				if((isNumeric(infixArray[i])) || (isVariable(infixArray[i]))) {
					double number;
					if(isNumeric(infixArray[i])){
						number = parseValue(infixArray[i]);
					}
					else{
						number = returnVariableValue(infixArray[i], values);
					}
					valuesArray.add(number);
				}//end if
				else if(isOperator(infixArray[i])) {
						Operator op = parseOperator(infixArray[i]);
						operatorsArray.add(op);
						if(op.equals(Operator.OPENPARENTHESES)) {
							openPar++;
						}
						else if(op.equals(Operator.CLOSEPARENTHESES)) {
							closePar++;
						}
				}
			}//end for

			if (openPar != closePar) {
				throw new IllegalStateException();
			}
			
			MyStack<Operator> operatorStack = new MyStack<Operator>();
			MyStack<Double> valueStack = new MyStack<Double>();
			
			double first, second, result = 0; 
			
			int countValues = 0;
			int countOp = 0;
			int count = -1;
			int valueStackSize = 0;
			int opStackSize = 0;
			
			
			valueStack.push(valuesArray.get(countValues)); countValues++; count++; valueStackSize++; 
			operatorStack.push(operatorsArray.get(countOp)); countOp++; count++; opStackSize++; 
			valueStack.push(valuesArray.get(countValues)); countValues++; count++; valueStackSize++;
			
			while((operatorsArray.size() > 1) && (count < infixArray.length - 1)) {
				if (countOp < operatorsArray.size()) { 
					
					while(operatorStack.peek().equals(Operator.OPENPARENTHESES)) {
						operatorStack.push(operatorsArray.get(countOp)); countOp++; count++; opStackSize++; 
					}//end while
					if((priorityOfOperator(operatorsArray.get(countOp)) <= priorityOfOperator(operatorStack.peek())) && (!(operatorsArray.get(countOp).equals(Operator.CLOSEPARENTHESES)))){ 		
						second = valueStack.pop(); valueStackSize--; 
						first = valueStack.pop(); valueStackSize--;
						Operator op = operatorStack.pop(); opStackSize--;
						result = calculate(first, op, second); 
						valueStack.push(result); 
						operatorStack.push(operatorsArray.get(countOp)); countOp++; count++; 	
							
						if ((valueStackSize < 2) && (countValues < valuesArray.size())) {
								valueStack.push(valuesArray.get(countValues)); countValues++; count++; valueStackSize++;
						}
						if((opStackSize < 1) && (countOp < operatorsArray.size())) {
								operatorStack.push(operatorsArray.get(countOp)); countOp++; count++; opStackSize++;
						}
					}//end if
					else if((priorityOfOperator(operatorsArray.get(countOp)) > priorityOfOperator(operatorStack.peek())) && (!(operatorsArray.get(countOp).equals(Operator.CLOSEPARENTHESES)))) {
						operatorStack.push(operatorsArray.get(countOp)); countOp++; count++; opStackSize++;
						valueStack.push(valuesArray.get(countValues)); countValues++; count++; valueStackSize++;
					}
					
					else if(operatorsArray.get(countOp).equals(Operator.CLOSEPARENTHESES)) {
						
						operatorStack.push(operatorsArray.get(countOp)); countOp++; count++; opStackSize++;
						
						while((!(operatorStack.isEmpty())) && (!(operatorStack.peek().equals(Operator.OPENPARENTHESES)))) {
							while(operatorStack.peek().equals(Operator.CLOSEPARENTHESES)) {
								operatorStack.pop(); opStackSize--;
							}
							second = valueStack.pop(); valueStackSize--; 
							first = valueStack.pop(); valueStackSize--; 
							Operator op = operatorStack.pop(); opStackSize--;
							result = calculate(first, op, second);
							valueStack.push(result);
						}//end while
						if(operatorStack.peek().equals(Operator.OPENPARENTHESES)){
							operatorStack.pop(); opStackSize--;
						}
						if ((valueStackSize < 2) && (countValues < valuesArray.size())) {
							valueStack.push(valuesArray.get(countValues)); countValues++; count++; valueStackSize++;
						}
						if((opStackSize < 1) && (countOp < operatorsArray.size())) {
							operatorStack.push(operatorsArray.get(countOp)); countOp++; count++; opStackSize++;
						}
						
					}//end else if
					
					}//end if
				
					else {
						count++;
					}
				
				}//end while
			
				while(!(operatorStack.isEmpty())) {
					second = valueStack.pop();
					first = valueStack.pop();
					Operator op = operatorStack.pop();
					result = calculate(first, op, second);
					valueStack.push(result);
				}//end while
	
			return valueStack.peek();
	    }//end evaluateInfix
	    /******************************************************************
	     * @param left
	     * @param op
	     * @param right
	     * evaluates expression based on the operator passed
	     *****************************************************************/
	    private static double calculate(double left, Operator op, double right){
	        switch (op){
	            case ADD: return left + right;
	            case SUBTRACT: return left - right;
	            case MULTIPLY: return left * right;
	            case DIVIDE: return left / right;
	            case POWER: return Math.pow(left, right);
	        }
	        return 0;
	    }//end calculate
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
	    }//end isNumeric
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
	    }//end isVariable
	    /*************************************************
	     * returns an int value of an operator's priority
	     ************************************************/
	    private static int priorityOfOperator(Operator op){
	        switch (op){
	            case ADD: case SUBTRACT: return 1;
	            case MULTIPLY: case DIVIDE: return 2;
	            case POWER: return 3;
	            case CLOSEPARENTHESES: return 4;
	            case OPENPARENTHESES: return 4;
	            //case BLANK: return 0;
	        }
	        return 0;
	    }//end prorityOfOperator
	    /*******************************************
	     * parses numeric string to a double
	     ******************************************/
	    private static double parseValue(String str) {
			double value = Double.parseDouble(str);
			return value;
		}//end returnDoubleValue    
	    /*****************************************
	     * parses string to enum type Operator
	     ****************************************/
	    private static Operator parseOperator(String opString) {
			switch(opString) {
			case "+": return Operator.ADD;
			case "-": return Operator.SUBTRACT;
			case "*": return Operator.MULTIPLY;
			case "/": return Operator.DIVIDE;
			case "^": return Operator.POWER;
			case "(": return Operator.OPENPARENTHESES;
			case ")": return Operator.CLOSEPARENTHESES;
			default: return null;
			}
		}//end parseOperator
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
		}//end isOperator
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
		}//end returnVariableValue
}//end class
