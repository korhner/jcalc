package io.korhner.jcalc.evaluation;

import io.korhner.jcalc.grammar.ExpressionLexer;
import io.korhner.jcalc.grammar.ExpressionParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class ExpressionEvaluator {

	public static double evaluate(final String expression) {
		ANTLRInputStream input = new ANTLRInputStream(expression);
		ExpressionLexer lexer = new ExpressionLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ExpressionParser parser = new ExpressionParser(tokens);
		ParseTree tree = parser.prog();
		ExpressionVisitor eval = new ExpressionVisitor();
		return eval.visit(tree);
	}
}
