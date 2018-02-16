/**
 * 
 */
package com.learn.oauth.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.oauth.model.LogEvent;
import com.learn.oauth.repository.LogEventRepository;

/**
 * @author vkalyana
 *
 */
@Service
public class LogEventServiceImpl implements LogEventService {

	@Autowired
	private LogEventRepository repository;
	
	/* (non-Javadoc)
	 * @see com.learn.oauth.business.LogEventService#log(com.learn.oauth.model.LogEvent)
	 */
	@Override
	public LogEvent log(LogEvent logEvent) {
		return this.repository.save(logEvent);
	}

}
