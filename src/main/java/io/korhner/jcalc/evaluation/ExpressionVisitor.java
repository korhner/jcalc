package io.korhner.jcalc.evaluation;

import io.korhner.jcalc.grammar.ExpressionBaseVisitor;
import io.korhner.jcalc.grammar.ExpressionParser;
import io.korhner.jcalc.grammar.ExpressionParser.AddSubContext;
import io.korhner.jcalc.grammar.ExpressionParser.ConstantContext;
import io.korhner.jcalc.grammar.ExpressionParser.MulDivContext;
import io.korhner.jcalc.grammar.ExpressionParser.ParensContext;

public class ExpressionVisitor extends ExpressionBaseVisitor<Double> {

	@Override
	public Double visitConstant(ConstantContext ctx) {
		return Double.valueOf(ctx.getText());
	}

	@Override
	public Double visitMulDiv(MulDivContext ctx) {
		double left = visit(ctx.expr(0));
		double right = visit(ctx.expr(1));
		double result = 0;

		switch (ctx.op.getType()) {
		case ExpressionParser.MUL:
			result = left * right;
			break;
		case ExpressionParser.DIV:
			result = left / right;
			break;
		}

		return result;
	}

	@Override
	public Double visitAddSub(AddSubContext ctx) {
		double left = visit(ctx.expr(0));
		double right = visit(ctx.expr(1));
		double result = 0;

		switch (ctx.op.getType()) {
		case ExpressionParser.ADD:
			result = left + right;
			break;
		case ExpressionParser.SUB:
			result = left - right;
			break;
		}

		return result;
	}
	
	@Override
	public Double visitParens(ParensContext ctx) {
		return visit(ctx.expr());
	}
}
