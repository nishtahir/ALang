package com.nishtahir;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class Main {
    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    Main(){
        try {
            ALangLexer lexer = new ALangLexer(new ANTLRFileStream("test.txt"));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            ALangParser parser = new ALangParser(tokenStream);
            ParserRuleContext tree =  parser.compilationUnit();
            ALangVisitor visitor = new ALangEvalVisitor();
            visitor.visit(tree);

            System.out.println("End parse ----");
        } catch (IOException e) {
            log.error("An unexpected error occurred", e);
        }
    }

    public static void main(String[] args) {
        new Main();
    }

}
