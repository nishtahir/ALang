package com.nishtahir.alang.error;

import com.nishtahir.alang.ALang;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation to listen for errors while parsing.
 * Provides Information to user about the nature and location
 * of errors that could not be parsed.
 */
public class ALangErrorListener extends BaseErrorListener {

    /**
     * Class logger.
     */
    private static final Logger LOGGER;

    /**
     *  Singleton instance.
     */
    private static ALangErrorListener instance;

    static {
        if (ALang.isVerboseOutput()) {
            LOGGER = LoggerFactory.getLogger(ALangErrorListener.class);
        } else {
            LOGGER = LoggerFactory
                    .getLogger(ALangErrorListener.class.getSimpleName());
        }
    }

    /**
     * @return Singleton instance of {@link ALangErrorListener}
     */
    public static ALangErrorListener getInstance() {
        if (instance == null) {
            instance = new ALangErrorListener();
        }

        return instance;
    }

    @Override
    public final void syntaxError(final Recognizer<?, ?> recognizer, final Object offendingSymbol, final int line,
                            final int charPositionInLine, final String msg, final RecognitionException e) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);

        String source = recognizer.getInputStream().getSourceName();
        if (source.isEmpty()) {
            return;
        }

        LOGGER.warn(source + " @ line:" + line + ". " + msg);
    }
}
