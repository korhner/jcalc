package io.korhner.jcalc.test;

import io.korhner.jcalc.evaluation.ExpressionEvaluator;

import org.junit.Assert;
import org.junit.Test;

public class TestGrammar {

	private boolean checkExpression(final String expression,
			final int expectedResult) {
		return ExpressionEvaluator.evaluate(expression) == expectedResult;
	}

	@Test
	public void simpleAddition() {
		Assert.assertTrue(checkExpression("2+3", 5));
	}
	
	@Test
	public void simpleSubstraction() {
		Assert.assertTrue(checkExpression("2-3", -1));
	}
	
	@Test
	public void simpleMultiplication() {
		Assert.assertTrue(checkExpression("2*3", 6));
	}
	
	@Test
	public void simpleDivision() {
		Assert.assertTrue(checkExpression("2/3", 0));
	}

}
