/**
 * 
 */
package com.learn.oauth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.oauth.business.LogEventService;
import com.learn.oauth.business.UserService;
import com.learn.oauth.exception.InvalidUserException;
import com.learn.oauth.model.LogEvent;
import com.learn.oauth.model.User;

/**
 * @author vkalyana
 *
 */
@RestController
@RequestMapping(path="user", produces=MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private LogEventService logEventService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("list")
	public  ResponseEntity<List<User>> findAllUsers() {
		this.logEventService.log(new LogEvent(User.class.getName(), "FETCH_ALL", ""));
		if(log.isDebugEnabled()) {
			log.debug("UserCtrl:: findAllUsers");
		}
		return new ResponseEntity<List<User>>(this.userService.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<User> getUserById(@PathVariable String id) {
		this.logEventService.log(new LogEvent(User.class.getName(), "FETCH", ""));
	    return new ResponseEntity<User>(this.userService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public  ResponseEntity<User> createUser(@RequestBody User user) {
		this.logEventService.log(new LogEvent(User.class.getName(), "CREATE", ""));
		if(log.isDebugEnabled()) {
			log.debug("Create User: "+ user);
		}
		try {
			user = this.userService.save(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (InvalidUserException e) {
			log.error(e.toString());
			this.logEventService.log(new LogEvent(User.class.getName(), "CREATE_ERROR", e.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/login")
	public  ResponseEntity<User> authenticate(@RequestBody User user) {
		this.logEventService.log(new LogEvent(User.class.getName(), "AUTHENTICATE", ""));
		if(log.isDebugEnabled()) {
			log.debug("Authenticate User: "+ user);
		}
		try {
			user = this.userService.findByUserNameAndPassword(user.getUserName(), user.getPassword());
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (InvalidUserException e) {
			log.error(e.toString());
			this.logEventService.log(new LogEvent(User.class.getName(), "AUTHENTICATE_ERROR", e.toString()));
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PutMapping("/update")
	public  ResponseEntity<User> put(@RequestBody User user) {
		this.logEventService.log(new LogEvent(User.class.getName(), "UPDATE", ""));
		if(log.isDebugEnabled()) {
			log.debug("Update User: "+ user);
		}
		try {
			user = this.userService.save(user);
			return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
		} catch (InvalidUserException e) {
			log.error(e.toString());
			this.logEventService.log(new LogEvent(User.class.getName(), "UPDATE_ERROR", e.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public  ResponseEntity<String> delete(@PathVariable String id) {
		this.logEventService.log(new LogEvent(User.class.getName(), "DELETE", ""));
		if(log.isDebugEnabled()) {
			log.debug("Delete User: "+ id);
		}
		try {
			this.userService.deleteOne(id);
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} catch (InvalidUserException e) {
			log.error(e.toString());
			this.logEventService.log(new LogEvent(User.class.getName(), "DELETE_ERROR", e.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PatchMapping("/patch")
	public  ResponseEntity<User> patch(@RequestBody User user) {
		this.logEventService.log(new LogEvent(User.class.getName(), "PATCH", ""));
		if(log.isDebugEnabled()) {
			log.debug("Patch update User: "+ user);
		}		
		try {
			user = this.userService.findAndUpdate(user);
			return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
		} catch (InvalidUserException e) {
			log.error(e.toString());
			this.logEventService.log(new LogEvent(User.class.getName(), "PATCH_ERROR", e.toString()));
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
