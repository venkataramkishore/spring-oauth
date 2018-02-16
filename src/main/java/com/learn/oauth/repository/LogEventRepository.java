/**
 * 
 */
package com.learn.oauth.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.oauth.model.LogEvent;

/**
 * @author vkalyana
 *
 */
public interface LogEventRepository extends MongoRepository<LogEvent, String> {

	List<LogEvent> findByEntity(String string);

}
