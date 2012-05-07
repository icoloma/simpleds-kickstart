package com.acme.log;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Filter out any {@link LogRecord} with level higher than this.
 * @author icoloma
 *
 */
public class MaxLevelFilter implements Filter {
	
    private final Level maxLevel;
    
    public MaxLevelFilter(Level level) {
        this.maxLevel = level;
    }
    
    @Override
	public boolean isLoggable(LogRecord record) {
        return maxLevel.intValue() > record.getLevel().intValue();
    }
    
}