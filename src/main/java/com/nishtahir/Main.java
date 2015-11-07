package com.nishtahir;

import com.nishtahir.value.Value;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


public class Main {
    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    /**
     * Table containing tokens and their reference values
     */
    private static Map<String, Value> terminalNodeTable = new Hashtable<>();

    Main(){
        try {
            ALangLexer lexer = new ALangLexer(new ANTLRFileStream("test.txt"));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            ALangParser parser = new ALangParser(tokenStream);
            ParserRuleContext tree =  parser.compilationUnit();
            ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
            ALangListener extractor = new Extractor();

            parseTreeWalker.walk(extractor, tree);

            System.out.println("End parse ----");
        } catch (IOException e) {
            log.error("An unexpected error occurred", e);
        }
    }

    public static void main(String[] args) {
        new Main();
    }

    public static Map<String, Value> getTerminalNodeTable() {
        return terminalNodeTable;
    }
}
