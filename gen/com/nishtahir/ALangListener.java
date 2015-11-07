// Generated from /Users/Nish/Developer/git/ALang/src/main/antlr4/com/nishtahir/ALang.g4 by ANTLR 4.5.1
package com.nishtahir;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ALangParser}.
 */
public interface ALangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ALangParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(ALangParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link ALangParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(ALangParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link ALangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ALangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ALangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ALangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ALangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(ALangParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ALangParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(ALangParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ALangParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(ALangParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ALangParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(ALangParser.ExpressionContext ctx);
}