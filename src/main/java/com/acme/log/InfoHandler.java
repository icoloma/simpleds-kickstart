package com.acme.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;


/**
 * Logs {@link Level#INFO} and smaller to {@link System#out}.
 * This class is inspired by {@link ConsoleHandler}
 * @author icoloma
 *
 */
public class InfoHandler extends StreamHandler {

    public InfoHandler() {
		setOutputStream(System.out);
		setFilter(new MaxLevelFilter(Level.WARNING));
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
