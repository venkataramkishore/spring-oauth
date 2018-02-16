/**
 * 
 */
package com.learn.oauth.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.oauth.exception.InvalidUserException;
import com.learn.oauth.model.User;
import com.learn.oauth.repository.UserRepository;

/**
 * @author vkalyana
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	/* (non-Javadoc)
	 * @see com.learn.oauth.business.UserService#findAll()
	 */
	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

	@Override
	public User findById(String id) {
		return this.repository.findOne(id);
	}

	@Override
	public User findByFirstName(String firstName) {
		return this.repository.findByFirstName(firstName);
	}

	@Override
	public void deleteOne(String id) throws InvalidUserException {
		try {
			this.repository.delete(id);
		} catch (Exception e) {
			throw new InvalidUserException("Delete User", id+" not valid:: "+e.getMessage());
		}
	}

	@Override
	public User save(User user) throws InvalidUserException {
		try {
			return this.repository.save(user);
		} catch (Exception e) {
			throw new InvalidUserException("Create User", e.getMessage());
		}
	}

	@Override
	public User findByUserNameAndPassword(String userName, String password) throws InvalidUserException {
		try {
			return this.repository.findByUserNameAndPassword(userName, password);
		} catch (Exception e) {
			throw new InvalidUserException("Authentication Failed", "Not a valid credentials");
		}
	}
	
	@Override
	public User findAndUpdate(User user) throws InvalidUserException {
		try {
//			User existingUser = this.repository.findOne(user.getId());
			return this.repository.save(user);
		} catch (Exception e) {
			throw new InvalidUserException("Update user Failed", e.getMessage());
		}
	}
}
