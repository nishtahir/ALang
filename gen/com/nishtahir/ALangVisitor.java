// Generated from /Users/Nish/Developer/git/ALang/src/main/antlr4/com/nishtahir/ALang.g4 by ANTLR 4.5.1
package com.nishtahir;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ALangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ALangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ALangParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(ALangParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ALangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ALangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ALangParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(ALangParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ALangParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(ALangParser.ExpressionContext ctx);
}