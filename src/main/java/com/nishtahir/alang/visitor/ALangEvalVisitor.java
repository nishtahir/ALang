package com.nishtahir.alang.visitor;

import com.nishtahir.alang.ALangBaseVisitor;
import com.nishtahir.alang.ALangParser;
import com.nishtahir.alang.evaluator.Operation;
import com.nishtahir.alang.evaluator.ValueEvaluator;
import com.nishtahir.alang.exception.UndeclaredVariableException;
import com.nishtahir.alang.exception.UnknownOperatorException;
import com.nishtahir.alang.exception.UnsupportedOperationException;
import com.nishtahir.alang.utils.StringUtils;
import com.nishtahir.alang.utils.ValueUtils;
import com.nishtahir.alang.value.BooleanValue;
import com.nishtahir.alang.value.IntegerValue;
import com.nishtahir.alang.value.ListValue;
import com.nishtahir.alang.value.StringValue;
import com.nishtahir.alang.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Visitor class to evaluate parser rules.
 */
public class ALangEvalVisitor extends ALangBaseVisitor<Value> {
    /**
     * Table containing tokens and their reference values.
     */
    private static Map<String, Value> tokenValueMap = new HashMap<>();


    private static final Logger LOGGER = LoggerFactory.getLogger(ALangEvalVisitor.class);

    @Override
    public Value visitIdentifierAssignment(final ALangParser.IdentifierAssignmentContext ctx) {
        String id = ctx.Identifier().getText();
        Value value = this.visit(ctx.expression());
        return tokenValueMap.put(id, value);
    }

    @Override
    public Value visitIndexAssignment(ALangParser.IndexAssignmentContext ctx) {
        String identifier = ctx.index().Identifier().getText();
        IntegerValue indexNo = ValueUtils.asIntegerValue(this.visit(ctx.index().expression()));

        ListValue value = ValueUtils.asListValue(tokenValueMap.get(identifier));
        Value expression = this.visit(ctx.expression());
        return value.setValueAtIndex(indexNo, expression);
    }

    @Override
    public Value visitExprAddSub(final ALangParser.ExprAddSubContext ctx) {
        Value lhs = this.visit(ctx.expression(0));
        Value rhs = this.visit(ctx.expression(1));

        try {
            switch (ctx.op.getType()) {
                case ALangParser.ADD:
                    return lhs.add(rhs);
                case ALangParser.SUB:
                    return lhs.subtract(rhs);
                default:
                    throw new UnknownOperatorException(ctx.op.getText(), ctx.getStart().getLine());
            }
        } catch (IllegalArgumentException e) {
            throw new UnsupportedOperationException(lhs, rhs, ctx.start.getLine());
        }
    }

    @Override
    public Value visitExprIncrDecr(ALangParser.ExprIncrDecrContext ctx) {
        Value lhs = this.visit(ctx.expression());

        try {
            switch (ctx.op.getType()) {
                case ALangParser.INCR:
                    return lhs.add(new IntegerValue(1));
                case ALangParser.DECR:
                    return lhs.subtract(new IntegerValue(1));
            }
        } catch (IllegalArgumentException e) {
//            throw new UnsupportedOperationException(lhs, ctx.start.getLine());
        }
        throw new UnknownOperatorException(ALangParser.tokenNames[ctx.op.getType()], ctx.start.getLine());
    }

    @Override
    public Value visitExprMultDiv(ALangParser.ExprMultDivContext ctx) {
        Value lhs = this.visit(ctx.expression(0));
        Value rhs = this.visit(ctx.expression(1));

        try {
            switch (ctx.op.getType()) {
                case ALangParser.MULT:
                    return lhs.multiply(rhs);
                case ALangParser.DIV:
                    return lhs.divide(rhs);
            }
        } catch (IllegalArgumentException e) {
            throw new UnsupportedOperationException(lhs, rhs, ctx.start.getLine());
        }
        throw new UnknownOperatorException(ALangParser.tokenNames[ctx.op.getType()], ctx.start.getLine());
    }

    @Override
    public Value visitExprMinMax(ALangParser.ExprMinMaxContext ctx) {
        Value lhs = this.visit(ctx.expression(0));
        Value rhs = this.visit(ctx.expression(1));

        try {
            switch (ctx.op.getType()) {
                case ALangParser.MIN:
                    return lhs.findMinimum(rhs);
                case ALangParser.MAX:
                    return lhs.findMaximum(rhs);
            }
        } catch (IllegalArgumentException e) {
            throw new UnsupportedOperationException(lhs, rhs, ctx.start.getLine());
        }
        throw new UnknownOperatorException(ALangParser.tokenNames[ctx.op.getType()], ctx.start.getLine());
    }

    @Override
    public Value visitExprBoolean(ALangParser.ExprBooleanContext ctx) {
        Value lhs = this.visit(ctx.expression(0));
        Value rhs = this.visit(ctx.expression(1));

        switch (ctx.op.getType()) {
            case ALangParser.GTR:
                return ValueEvaluator.evaluate(lhs, rhs, Operation.Greater);
            case ALangParser.LESS:
                return ValueEvaluator.evaluate(lhs, rhs, Operation.Less);
            case ALangParser.EQL:
                return lhs.equals(rhs);
            case ALangParser.NEQL:
                BooleanValue value = (BooleanValue) lhs.equals(rhs);
                value.setValue(!value.getValue());
                return value;
            default:
                throw new UnknownOperatorException(ALangParser.tokenNames[ctx.op.getType()], ctx.start.getLine());
        }
    }

    @Override
    public Value visitLiteralIdentifier(ALangParser.LiteralIdentifierContext ctx) {
        String identifier = ctx.getText();

        Value value = tokenValueMap.get(identifier);
        if (value == null) {
            throw new UndeclaredVariableException(identifier, ctx.start.getLine());
        }
        return value;
    }

    @Override
    public Value visitLiteralString(ALangParser.LiteralStringContext ctx) {
        return new StringValue(StringUtils.clean(ctx.getText()));
    }

    @Override
    public Value visitLiteralNumber(ALangParser.LiteralNumberContext ctx) {
        return new IntegerValue(ctx.getText());
    }

    @Override
    public Value visitPrint(ALangParser.PrintContext ctx) {
        System.out.print(this.visit(ctx.parameter()));
        return super.visitPrint(ctx);
    }

    @Override
    public Value visitPrintLine(ALangParser.PrintLineContext ctx) {
        System.out.println(this.visit(ctx.parameter()));
        return super.visitPrintLine(ctx);
    }

    @Override
    public Value visitForLoop(ALangParser.ForLoopContext ctx) {
        String identifier = ctx.Identifier().getText();

        ALangParser.RangeContext rangeCtx = ctx.range();
        IntegerValue lhs = ValueUtils.asIntegerValue(this.visit(rangeCtx.expression(0)));
        IntegerValue rhs = ValueUtils.asIntegerValue(this.visit(rangeCtx.expression(1)));

        IntegerValue loopCounter = new IntegerValue(lhs.getValue());
        tokenValueMap.put(identifier, loopCounter);

        if (lhs.compareTo(rhs) < 0) {
            for (int i = lhs.getValue(); i <= rhs.getValue(); i++) {
                loopCounter.setValue(i);
                tokenValueMap.put(identifier, loopCounter);
                this.visit(ctx.statements());
            }
        } else if (lhs.compareTo(rhs) > 0) {
            for (int i = lhs.getValue(); i >= rhs.getValue(); i--) {
                loopCounter.setValue(i);
                tokenValueMap.put(identifier, loopCounter);
                this.visit(ctx.statements());
            }
        } else {
            this.visit(ctx.statements());
        }


        return tokenValueMap.remove(identifier);
    }

    @Override
    public Value visitIfStatement(ALangParser.IfStatementContext ctx) {

        BooleanValue condition = (BooleanValue) this.visit(ctx.expression());
        if (condition.getValue()) {
            return this.visitStatements(ctx.statements(0));
        } else {
            if (ctx.statements(1) != null)
                return this.visitStatements(ctx.statements(1));
        }
        return super.visitIfStatement(ctx);
    }

    @Override
    public Value visitRange(ALangParser.RangeContext ctx) {
        Value lhs = this.visit(ctx.expression(0));
        Value rhs = this.visit(ctx.expression(1));

        return lhs.subtract(rhs);
    }

    @Override
    public Value visitExpressionList(ALangParser.ExpressionListContext ctx) {
        ListValue value = new ListValue();
        for (ALangParser.ExpressionContext exp : ctx.expression())
            value.getValue().add(this.visit(exp));
        return value;
    }

    @Override
    public Value visitIndex(ALangParser.IndexContext ctx) {
        String identifier = ctx.Identifier().getText();
        ListValue value = ValueUtils.asListValue(tokenValueMap.get(identifier));
        IntegerValue index = ValueUtils.asIntegerValue(this.visit(ctx.expression()));

        return value.getValueAtIndex(index);
    }

    @Override
    public Value visitWhileLoop(ALangParser.WhileLoopContext ctx) {
        while (((BooleanValue) this.visit(ctx.expression())).getValue()) {
            this.visit(ctx.statements());
        }
        return null;
    }

    /**
     *
     * @return
     */
    public static Map<String, Value> getTokenValueMap() {
        return tokenValueMap;
    }

}
