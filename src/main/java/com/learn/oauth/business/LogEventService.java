/**
 * 
 */
package com.learn.oauth.business;

import com.learn.oauth.model.LogEvent;

/**
 * @author vkalyana
 *
 */
public interface LogEventService {

	/**
	 * Record all api/error events
	 * @param logEvent
	 * @return LogEvent
	 */
	LogEvent log(LogEvent logEvent);
}
