/**
 * 
 */
package com.learn.oauth.business;

import java.util.List;

import com.learn.oauth.exception.InvalidUserException;
import com.learn.oauth.model.User;

/**
 * @author vkalyana
 *
 */
public interface UserService {

	/**
	 * Find all users available in system
	 * @return List<User>
	 */
	List<User> findAll();
	
	/**
	 * Find user by ID
	 * @return User
	 */
	User findById(String id);
	
	/**
	 * Find user by first name
	 * @param firstName
	 * @return User
	 */
	User findByFirstName(String firstName);
	
	/**
	 * Delete user, returns deleted user
	 * @param id
	 * @return User
	 * @throws InvalidUserException
	 */
	void deleteOne(String id) throws InvalidUserException;
	
	/**
	 * Save user, return saved user
	 * @param user
	 * @return User
	 * @throws InvalidUserException
	 */
	User save(User user) throws InvalidUserException;
	
	/**
	 * Validate user with given credentials 
	 * @param userName
	 * @param password
	 * @return User
	 * @throws InvalidUserException
	 */
	User findByUserNameAndPassword(String userName, String password) throws InvalidUserException;
	
	/**
	 * Find user by ID and update the given fields and return the updated user
	 * @param user
	 * @return User
	 * @throws InvalidUserException
	 */
	User findAndUpdate(User user) throws InvalidUserException;
}
