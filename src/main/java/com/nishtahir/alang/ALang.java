package com.nishtahir.alang;

import com.nishtahir.alang.error.ALangErrorListener;
import com.nishtahir.alang.visitor.ALangEvalVisitor;
import com.nishtahir.alang.visitor.ALangPicoVisitor;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * ALang is a small dynamically typed interpreted language
 * that runs on the JavaVM.
 *
 * @author Nish Tahir
 * @since 0.0.1
 */
public class ALang {

    /**
     * Class logger.
     */
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ALang.class);

    /**
     * Application name.
     */
    public static final String APP_NAME = "ALang";

    /**
     * Application Version number. It follows the pattern - Major.Minor.Patch
     */
    public static final String VERSION = "0.0.1";

    /**
     * Copyright information. See license file for more information
     */
    public static final String COPYRIGHT = "Copyright Nish Tahir 2015";


    /**
     * Application wide switch to indicate whether
     * to display verboseOption output.
     */
    private static boolean verboseOutput = false;

    /**
     * @inheritDoc
     */
    private Options commandLineOptions;

    /**
     * Visitor used to parse the given files. Defaults to
     * the interpreter, but can be changed using comman
     * line flags.
     */
    private ALangVisitor visitor = new ALangEvalVisitor();


    /**
     * @param args command line arguments
     */
    public ALang(final String[] args) {
        commandLineOptions = new Options();

        commandLineOptions.addOption(
                new Option("h", "help", false, "print this message"));
        commandLineOptions.addOption(
                new Option("version", false, "more info about " + APP_NAME));
        commandLineOptions.addOption(
                new Option("v", "verbose", false, "show more information"));

        commandLineOptions.addOption(
                new Option("c", "compiler", true, "compiler option [pico]"));


        try {
            List<String> filesToParse = parseCommandLineArguments(args);
            if (filesToParse != null && !filesToParse.isEmpty()) {
                parseFiles(filesToParse);
            }
        } catch (ParseException e) {
            LOGGER.error("An error occurred while parsing command line", e);
        } catch (IOException e) {
            LOGGER.error("An IOException occurred", e);
        }
    }

    /**
     * @param args commandline arguments
     */
    public static void main(final String[] args) {
        BasicConfigurator.configure();
        new ALang(args);
    }


    /**
     * @return application wide verboseOption setting
     */
    public static boolean isVerboseOutput() {
        return verboseOutput;
    }

    /**
     * @param outputMode output mode
     */
    public static void setVerboseOutput(final boolean outputMode) {
        verboseOutput = outputMode;
    }

    /**
     * @param args command line arguments
     * @return List of files to parse
     * @throws ParseException {@link ParseException}
     */
    private List<String> parseCommandLineArguments(final String[] args)
            throws ParseException {
        CommandLineParser commandLineParser = new DefaultParser();

        CommandLine commandLine =
                commandLineParser.parse(commandLineOptions, args);

        if (commandLine.hasOption("h")) {
            displayHelpInformation();
        }

        if (commandLine.hasOption("version")) {
            displayVersionInformation();
        }

        if (commandLine.hasOption("v")) {
            setVerboseOutput(true);
        }

        if (commandLine.hasOption("c")) {
            switch (commandLine.getOptionValue("c")) {
                case "pico":
                    visitor = new ALangPicoVisitor();
            }
        }

        return commandLine.getArgList();

    }

    /**
     * @param paths path to files to be parsed
     * @throws IOException {@link IOException}
     */
    private void parseFiles(final List<String> paths) throws IOException {
        for (String path : paths) {
            parseFile(path);
        }
    }

    /**
     * @param path path to file to be parsed
     * @throws IOException {@link IOException}
     */
    private void parseFile(final String path) throws IOException {
        File file = FileUtils.getFile(path);
        if (file.exists()) {
            ALangLexer lexer = new ALangLexer(new ANTLRFileStream(path));
            lexer.removeErrorListeners();
            lexer.addErrorListener(ALangErrorListener.getInstance());

            CommonTokenStream tokenStream = new CommonTokenStream(lexer);

            ALangParser parser = new ALangParser(tokenStream);
            parser.removeErrorListeners();
            parser.addErrorListener(ALangErrorListener.getInstance());

            ParserRuleContext tree = parser.compilationUnit();

            try {
                visitor.visit(tree);
            } catch (Exception e) {
                if (isVerboseOutput()) {
                    e.printStackTrace();
                }
            }
        } else {
            LOGGER.error(file.getAbsolutePath() + " is not a valid file path.");
        }
    }

    /**
     * Display simple help information containing a list
     * of command line options.
     */
    private void displayHelpInformation() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("[file]", commandLineOptions);
        System.exit(0);
    }

    /**
     * Display version information including Application
     * name, version and copyright information.
     */
    private void displayVersionInformation() {
        System.out.println(APP_NAME + " " + VERSION + " " + COPYRIGHT);
        System.exit(0);
    }
}
