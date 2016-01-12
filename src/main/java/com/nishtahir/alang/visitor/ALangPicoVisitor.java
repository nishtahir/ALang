package com.nishtahir.alang.visitor;

import com.nishtahir.alang.ALangParser;
import com.nishtahir.alang.value.IntegerValue;
import com.nishtahir.alang.value.ListValue;
import com.nishtahir.alang.value.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Used to output Pico cpu compatible assembly code.
 */
public class ALangPicoVisitor extends ALangEvalVisitor {
    String beginWhileTag; // this should not be here. need better soln
    String endWhileTag;

    /**
     * Max available allocatable memory.
     */
    private static final int MAX_ADDR = 128;

    /**
     * Map used to allocate memory on the CPU. We aren't sure of the
     * On the CPU the size is 0-255 -> 256
     */
    private static List<String> memoryList = new ArrayList<>(MAX_ADDR);

    /**
     * This is not an efficient way of doing things.
     * But it works.
     */
    private static int memPointer = 0;

    /**
     *
     */
    Logger LOGGER = LoggerFactory.getLogger(ALangPicoVisitor.class);

    @Override
    public Value visitCompilationUnit(ALangParser.CompilationUnitContext ctx) {
        this.visit(ctx.statements());
        System.out.println("HALT");
        return null;
    }

    @Override
    public Value visitIdentifierAssignment(ALangParser.IdentifierAssignmentContext ctx) {
        String id = ctx.Identifier().getText();
        Value value = this.visit(ctx.expression());

        System.out.println(";" + id);
        if (!getTokenValueMap().containsKey(id) && !memoryList.contains(id)) {

            if (value instanceof ListValue) {
                ListValue v = (ListValue) value;
                for(int i = 0; i < v.getValue().size(); i++){
                    String val = id + i;
                    System.out.println("$" + val + " " +  "0x" + memPointer);
                    memoryList.add(val);

                    System.out.println("LOADN " + v.getValue().get(i));
                    System.out.println("STORE " + "$" + id + i + "\n\n");
                    memPointer++;
                }


            } else {
                memoryList.add(id);
                System.out.println("$" + id + " " + "0x" + memPointer);
                memPointer++;
//                System.out.println("LOADN " + value);
                System.out.println("STORE " + "$" + id + "\n\n");
            }
        } else {
            System.out.println("STORE " + "$" + id + "\n\n");
        }

        return null;
    }

    @Override
    public Value visitExprBoolean(ALangParser.ExprBooleanContext ctx) {
        String[] ids = ctx.getText().split(ctx.op.getText());
        System.out.println("LOADV $" + ids[0]);

        System.out.println("SUB " + ids[1]);
        switch (ctx.op.getType()) {
            case ALangParser.GTR:
                break;

            case ALangParser.LESS:

                break;
            case ALangParser.EQL:
                System.out.println("INV");
                System.out.print("BIZ ");
//                System.out.println("JMP " + endWhileTag);
                break;
            case ALangParser.NEQL:
                System.out.print("BIZ ");
//                System.out.println("JMP " + endWhileTag);

                break;
        }
        return null;
    }

    @Override
    public Value visitWhileLoop(ALangParser.WhileLoopContext ctx) {
        beginWhileTag = UUID.randomUUID().toString().substring(0, 8);
        endWhileTag = UUID.randomUUID().toString().substring(0, 8);

        System.out.println(beginWhileTag + ":");

        //Do all the stuff that my boolean expression should do
        this.visit(ctx.expression());
        System.out.println(endWhileTag);

        //Do all the stuff that statements should do
        this.visit(ctx.statements());
        System.out.println("JMP " + beginWhileTag);
        //if(neqlFlag==1)
        System.out.println(endWhileTag + ":");
        return null;
    }

    @Override
    public Value visitExprIncrDecr(ALangParser.ExprIncrDecrContext ctx) {
        String[] value = ctx.getText().split(ctx.op.getText());
        System.out.println("LOADV $" + value[0]);
            switch (ctx.op.getType()) {
                case ALangParser.INCR:
                    System.out.println("INC");
                    break;
                case ALangParser.DECR:
                    System.out.println("DEC");
                    break;

            }

        System.out.println("STORE $" + value[0]);
        return null;
    }

    @Override
    public Value visitExprAddSub(ALangParser.ExprAddSubContext ctx) {
        String[] ids = new String[2];
        switch (ctx.op.getType()) {
            case ALangParser.ADD:
                ids = ctx.getText().split("\\+");
                break;
            case ALangParser.SUB:
                ids = ctx.getText().split("-");
                break;

        }
        this.visit(ctx.expression(0));
        System.out.println("SETR");
        this.visit(ctx.expression(1));
        //System.out.println("LOADA"); // this is :D
        System.out.println("MUXR");

        switch (ctx.op.getType()) {
            case ALangParser.ADD:
                System.out.println("ADD");
                break;
            case ALangParser.SUB:
                System.out.println("SUB");
                break;

        }
        System.out.println("MUXOP");
        //System.out.println("STORE $" + ids[0]); testing
        return null;
    }

    @Override
    public Value visitLiteralIdentifier(ALangParser.LiteralIdentifierContext ctx) {
        System.out.println("LOADV $" + ctx.Identifier().getText());
        return null;
    }

    @Override
    public Value visitIndex(ALangParser.IndexContext ctx) {
        String[] ids = ctx.getText().split("\\^");

        IntegerValue val = (IntegerValue) getTokenValueMap().get(ids[1]);
        System.out.println("LOADV " + findInMemoryList(ids[0]+val.getValue()));
        System.out.println("LOADA");
        return null;
    }

    @Override
    public Value visitLiteralNumber(ALangParser.LiteralNumberContext ctx) {
        System.out.println("LOADN " + ctx.NumberLiteral().getText());
        return null;
    }

    private int findInMemoryList(String val){
        for(int i = 0; i < memoryList.size(); i++){
            if(val.equals(memoryList.get(i))){
                return i;
            }
        }
        return -1;
    }

}

