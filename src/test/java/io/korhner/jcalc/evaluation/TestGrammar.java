package io.korhner.jcalc.evaluation;

import io.korhner.jcalc.evaluation.ExpressionEvaluator;

import org.junit.Assert;
import org.junit.Test;

public class TestGrammar {

	private static final double allowedError = 0.0001;

	@Test
	public void simpleArithmetic() {
		Assert.assertEquals(ExpressionEvaluator.evaluate("2+3").getNumber(), 5,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("10-2").getNumber(),
				8, allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("0*3+1").getNumber(), 1,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("1/3").getNumber(), 0.333333,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("-2.5*-2").getNumber(), 5,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("-.5*-.5").getNumber(), 0.25,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("10%2").getNumber(), 0,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("10%3").getNumber(), 1,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2^3").getNumber(), 8,
				allowedError);
	}
	
	@Test
	public void rightAssociativity() {
		Assert.assertEquals(ExpressionEvaluator.evaluate("2^3^2").getNumber(), 512,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("(2^1)^2").getNumber(), 4,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("1+2^2*3").getNumber(), 13,
				allowedError);
	}
	
	@Test
	public void numberVariations() {
		Assert.assertEquals(ExpressionEvaluator.evaluate("1").getNumber(), 1,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("-1").getNumber(), -1,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("+1").getNumber(), 1,
				allowedError);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("1.1").getNumber(), 1.1,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate(".1").getNumber(), .1,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("1.").getNumber(), 1.,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("+1.").getNumber(), 1,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("-.8").getNumber(), -0.8,
				allowedError);
	}

	@Test
	public void arithmeticPrecedence() {
		Assert.assertEquals(ExpressionEvaluator.evaluate("2+3*-2").getNumber(), -4,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("(2+3)*-2").getNumber(), -10,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("((2.1+3)+(4+1*2))*3").getNumber(), 33.3,
				allowedError);
		Assert.assertEquals(ExpressionEvaluator.evaluate("1+3%2").getNumber(), 2,
				allowedError);
	}

	@Test
	public void simpleLogical() {
		Assert.assertEquals(ExpressionEvaluator.evaluate("!T").getBool(), false);
		Assert.assertEquals(ExpressionEvaluator.evaluate("!F").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("!!T").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("T").getBool(), true);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("2<3.5").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2<2").getBool(), false);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2<1.1").getBool(), false);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("2<=3.5").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2<=2").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2<=1.1").getBool(), false);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("2>3.5").getBool(), false);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2>2").getBool(), false);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2>-5.1").getBool(), true);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("2>=3.5").getBool(), false);
		Assert.assertEquals(ExpressionEvaluator.evaluate("2>=2").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("-2>=5.1").getBool(), false);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("T&&T").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("F&&T").getBool(), false);
		Assert.assertEquals(ExpressionEvaluator.evaluate("F&&F").getBool(), false);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("T||F").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("F||F").getBool(), false);
		
		Assert.assertEquals(ExpressionEvaluator.evaluate("T==T").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("T==F").getBool(), false);
		Assert.assertEquals(ExpressionEvaluator.evaluate("!T!=F").getBool(), false);
	}
	
	@Test
	public void logicalPrecedence() {
		Assert.assertEquals(ExpressionEvaluator.evaluate("!(T && F) == (!T || !F)").getBool(), true);
		Assert.assertEquals(ExpressionEvaluator.evaluate("!(1<2 || 2>1) == (1>=2 && 2<=1)").getBool(), true);
	}
	

}
