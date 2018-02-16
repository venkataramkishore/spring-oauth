/**
 * 
 */
package com.learn.oauth;

import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Component;

import com.mongodb.DB;

/**
 * @author vkalyana
 *
 */
@Component
public class MongoDBComponent {

	
	private final MongoDbFactory dbFactory;
	
	public MongoDBComponent(MongoDbFactory mongoFactory) {
		this.dbFactory = mongoFactory;
	}
	
	public DB myDatabase() {
		return this.dbFactory.getDb();
	}
	
}
