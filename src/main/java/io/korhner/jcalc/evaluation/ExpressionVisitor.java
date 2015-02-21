package io.korhner.jcalc.evaluation;

import io.korhner.jcalc.grammar.ExpressionBaseVisitor;
import io.korhner.jcalc.grammar.ExpressionParser;
import io.korhner.jcalc.grammar.ExpressionParser.AddSubContext;
import io.korhner.jcalc.grammar.ExpressionParser.MulDivContext;
import io.korhner.jcalc.grammar.ExpressionParser.NumberContext;
import io.korhner.jcalc.grammar.ExpressionParser.ParensContext;

public class ExpressionVisitor extends ExpressionBaseVisitor<Integer> {

	@Override
	public Integer visitNumber(NumberContext ctx) {
		return Integer.valueOf(ctx.NUMBER().getText());
	}

	@Override
	public Integer visitMulDiv(MulDivContext ctx) {
		int left = visit(ctx.expr(0));
		int right = visit(ctx.expr(1));
		int result = 0;

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
	public Integer visitAddSub(AddSubContext ctx) {
		int left = visit(ctx.expr(0));
		int right = visit(ctx.expr(1));
		int result = 0;

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
	public Integer visitParens(ParensContext ctx) {
		return visit(ctx.expr());
	}
}
