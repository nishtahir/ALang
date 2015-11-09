package com.nishtahir;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private String[] args;
    private Options options = new Options();
    private Option help = new Option("h", "help", false, "print this message");
    private Option version = new Option("version", false,
            "more info about " + ALangApplication.APP_NAME);
    private Option verbose = new Option("v", "verbose", false,
            "show more information");

    public Main(String[] args) {
        this.args = args;
        initOptions();
    }

    public static void main(String[] args) {
        new Main(args).parse();
    }

    private void parse() {
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = commandLineParser.parse(options, args);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        if (cmd.hasOption("h")) {
            displayHelpInfo();
        }

        if (cmd.hasOption("version")) {
            displayVersionInfo();
        }

        try {
            List<String> trailingArgs = cmd.getArgList();
            for (String path : trailingArgs) {
                File file = FileUtils.getFile(path);
                if (file.exists()) {
                    ALangLexer lexer = new ALangLexer(new ANTLRFileStream(path));
                    CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                    ALangParser parser = new ALangParser(tokenStream);
                    ParserRuleContext tree = parser.compilationUnit();
                    ALangVisitor visitor = new ALangEvalVisitor();
                    visitor.visit(tree);
                } else {
                    log.error(file.getAbsolutePath() + " is not a valid file path.");
                    return;
                }
            }
        } catch (IOException e) {
            log.error("An unexpected error occurred", e);
        }
    }

    public void displayHelpInfo() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("[file]", options);
        System.exit(0);
    }

    public void displayVersionInfo() {
        System.out.println("Alang" + ALangApplication.VERSION + ", " + ALangApplication.COPYRIGHT);
        System.exit(0);
    }

    public void initOptions() {
        options.addOption(help);
        options.addOption(verbose);
        options.addOption(version);
    }
}