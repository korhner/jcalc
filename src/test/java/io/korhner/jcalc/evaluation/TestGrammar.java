package io.korhner.jcalc.evaluation;

import io.korhner.jcalc.evaluation.ExpressionEvaluator;

import org.junit.Assert;
import org.junit.Test;

public class TestGrammar {

	private static final double allowedError = 0.0001;

	@Test
	public void simpleAddition() {
		String expression = "2+3";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = 5;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void simpleSubstraction() {
		String expression = "2-3";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = -1;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void simpleMultiplication() {
		String expression = "2*3";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = 6;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void simpleDivision() {
		String expression = "2/3";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = 0.666666;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void signedInteger() {
		String expression = "-2-3";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = -5;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void signedDouble() {
		String expression = "-2.5*-2";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = 5;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void shortDouble() {
		String expression = "-.5*.5";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = -0.25;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void multipleOperations() {
		String expression = "2-3+245";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = 244;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void multiplicationPrecedence() {
		String expression = "2+3*-2";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = -4;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void parensPrecedence() {
		String expression = "(2+3)*-2";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = -10;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void nestedParens() {
		String expression = "((2.1+3)+(4+1*2))*3";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = 33.3;
		
		Assert.assertEquals(result, expected, allowedError);
	}
	
	@Test
	public void moduo() {
		String expression = "1+5%2";
		double result = ExpressionEvaluator.evaluate(expression);
		double expected = 2;
		
		Assert.assertEquals(result, expected, allowedError);
	}

}
