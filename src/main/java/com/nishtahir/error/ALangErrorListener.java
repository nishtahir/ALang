package com.nishtahir.error;

import com.nishtahir.ALangApplication;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ALangErrorListener extends BaseErrorListener {
    private static final Logger log;

    static {
        if(ALangApplication.MODE_VERBOSE){
            log = LoggerFactory.getLogger(ALangErrorListener.class);
        } else {
            log = LoggerFactory.getLogger(ALangErrorListener.class.getSimpleName());
        }
    }

    private static ALangErrorListener instance;

    public static ALangErrorListener getInstance() {
        if (instance == null) {
            instance = new ALangErrorListener();
        }

        return instance;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line,
                            int charPositionInLine, String msg, RecognitionException e) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);

        String source = recognizer.getInputStream().getSourceName();
        if(source.isEmpty()){
            return;
        }

        log.warn(source + " @ line:" + line + ". " + msg);
    }
}