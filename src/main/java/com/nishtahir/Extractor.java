package com.nishtahir;

import com.nishtahir.value.Value;

/**
 * Created by Nish on 11/7/15.
 */
public class Extractor extends ALangListenerImpl {

    @Override
    public void enterPrintStatement(ALangParser.PrintStatementContext ctx) {
        super.enterPrintStatement(ctx);
        ALangParser.ParameterContext parameter = ctx.parameter();
        if (parameter.value != null) {
            System.out.println(parameter.value);
        } else {
            Value value = Main.getTerminalNodeTable().get(parameter.Identifier().getText());
            System.out.println(value.toString());
        }
    }

    @Override
    public void enterAssignment(ALangParser.AssignmentContext ctx) {
        super.enterAssignment(ctx);
        Main.getTerminalNodeTable().put(ctx.Identifier().toString(), ctx.expression().value);
    }
}
