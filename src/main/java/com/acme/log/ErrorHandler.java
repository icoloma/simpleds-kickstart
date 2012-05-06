package com.acme.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;


/**
 * Logs {@link Level#WARNING} and greater to {@link System#err}.
 * This class is inspired by {@link ConsoleHandler}
 * @author icoloma
 *
 */
public class ErrorHandler extends StreamHandler {

	public ErrorHandler() {
		setOutputStream(System.err);
	}
	
    @Override
	public void publish(LogRecord record) {
		super.publish(record);	
		flush();
    }

    @Override
	public void close() {
    	flush();
    }
    
}
