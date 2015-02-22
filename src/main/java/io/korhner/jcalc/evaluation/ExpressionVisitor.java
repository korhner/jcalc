package io.korhner.jcalc.evaluation;

import io.korhner.jcalc.grammar.ExpressionBaseVisitor;
import io.korhner.jcalc.grammar.ExpressionParser;
import io.korhner.jcalc.grammar.ExpressionParser.AdditionOperationContext;
import io.korhner.jcalc.grammar.ExpressionParser.AndOperationContext;
import io.korhner.jcalc.grammar.ExpressionParser.BoolEqualityOperationContext;
import io.korhner.jcalc.grammar.ExpressionParser.BoolParenthesesContext;
import io.korhner.jcalc.grammar.ExpressionParser.BoolValueContext;
import io.korhner.jcalc.grammar.ExpressionParser.MultiplicationOperationContext;
import io.korhner.jcalc.grammar.ExpressionParser.NumberEqualityOperationContext;
import io.korhner.jcalc.grammar.ExpressionParser.NumberParenthesesContext;
import io.korhner.jcalc.grammar.ExpressionParser.NumberValueContext;
import io.korhner.jcalc.grammar.ExpressionParser.OrOperationContext;
import io.korhner.jcalc.grammar.ExpressionParser.RelationalOperationContext;
import io.korhner.jcalc.grammar.ExpressionParser.UnaryOperationContext;
import io.korhner.jcalc.value.Value;

public class ExpressionVisitor extends ExpressionBaseVisitor<Value> {

	@Override
	public Value visitMultiplicationOperation(MultiplicationOperationContext ctx) {
		double left = visit(ctx.numExpr(0)).getNumber();
		double right = visit(ctx.numExpr(1)).getNumber();
		double result = 0;

		switch (ctx.mulPrecedence().op.getType()) {
		case ExpressionParser.MUL:
			result = left * right;
			break;
		case ExpressionParser.DIV:
			result = left / right;
			break;
		case ExpressionParser.MODUO:
			result = left % right;
			break;
		}

		return new Value(result);
	}

	@Override
	public Value visitAdditionOperation(AdditionOperationContext ctx) {
		double left = visit(ctx.numExpr(0)).getNumber();
		double right = visit(ctx.numExpr(1)).getNumber();
		double result = 0;

		switch (ctx.addPrecedence().op.getType()) {
		case ExpressionParser.PLUS:
			result = left + right;
			break;
		case ExpressionParser.MINUS:
			result = left - right;
			break;
		}

		return new Value(result);
	}

	@Override
	public Value visitNumberValue(NumberValueContext ctx) {
		return new Value(Double.valueOf(ctx.getText()));
	}

	@Override
	public Value visitNumberParentheses(NumberParenthesesContext ctx) {
		return visit(ctx.numExpr());
	}

	@Override
	public Value visitUnaryOperation(UnaryOperationContext ctx) {
		boolean result = !visit(ctx.boolExpr()).getBool();
		return new Value(result);
	}

	@Override
	public Value visitRelationalOperation(RelationalOperationContext ctx) {
		double left = visit(ctx.numExpr(0)).getNumber();
		double right = visit(ctx.numExpr(1)).getNumber();
		boolean result = false;

		switch (ctx.relPrecedence().op.getType()) {
		case ExpressionParser.LT:
			result = left < right;
			break;

		case ExpressionParser.LTEQ:
			result = left <= right;
			break;

		case ExpressionParser.GT:
			result = left > right;
			break;

		case ExpressionParser.GTEQ:
			result = left >= right;
			break;
		}

		return new Value(result);
	}

	@Override
	public Value visitNumberEqualityOperation(NumberEqualityOperationContext ctx) {
		double left = visit(ctx.numExpr(0)).getNumber();
		double right = visit(ctx.numExpr(1)).getNumber();
		boolean result = false;

		switch (ctx.eqPrecedence().op.getType()) {
		case ExpressionParser.EQ:
			result = left == right;
			break;
		case ExpressionParser.NOTEQ:
			result = left != right;
			break;
		}

		return new Value(result);
	}

	@Override
	public Value visitBoolEqualityOperation(BoolEqualityOperationContext ctx) {
		boolean left = visit(ctx.boolExpr(0)).getBool();
		boolean right = visit(ctx.boolExpr(1)).getBool();
		boolean result = false;

		switch (ctx.eqPrecedence().op.getType()) {
		case ExpressionParser.EQ:
			result = left == right;
			break;
		case ExpressionParser.NOTEQ:
			result = left != right;
			break;
		}

		return new Value(result);
	}

	@Override
	public Value visitAndOperation(AndOperationContext ctx) {
		boolean left = visit(ctx.boolExpr(0)).getBool();
		boolean right = visit(ctx.boolExpr(1)).getBool();
		boolean result = left && right;
		return new Value(result);
	}

	@Override
	public Value visitOrOperation(OrOperationContext ctx) {
		boolean left = visit(ctx.boolExpr(0)).getBool();
		boolean right = visit(ctx.boolExpr(1)).getBool();
		boolean result = left || right;
		return new Value(result);
	}

	@Override
	public Value visitBoolValue(BoolValueContext ctx) {
		return new Value(
				ctx.boolConstant().op.getType() == ExpressionParser.TRUE);
	}

	@Override
	public Value visitBoolParentheses(BoolParenthesesContext ctx) {
		return visit(ctx.boolExpr());
	}
}
