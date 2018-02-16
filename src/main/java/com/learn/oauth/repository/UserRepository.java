/**
 * 
 */
package com.learn.oauth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.learn.oauth.model.User;

/**
 * @author vkalyana
 * A User repository to maintain user details
 */
public interface UserRepository extends MongoRepository<User, String> {
	

	/**
	 * Find user by his first name
	 * @param firstName
	 * @return User
	 */
    User findByFirstName(String firstName);
    
    /**
     * Validate user credentials against storage      
     * @param userName
     * @param password
     * @return User
     */
    User findByUserNameAndPassword(String userName, String password);
    
}
