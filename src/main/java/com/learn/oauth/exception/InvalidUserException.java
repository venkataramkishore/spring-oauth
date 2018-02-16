/**
 * 
 */
package com.learn.oauth.exception;

import java.util.Objects;

/**
 * @author vkalyana
 *
 */
public class InvalidUserException extends Exception {

	private static final long serialVersionUID = 2039310308281572756L;
	private String title;
	private String message;
	
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}


	/**
	 * Default constructor with fixed 
	 */
	public InvalidUserException() {
		super();
		this.title = "Authentication Failed";
		this.message = "Not a valid credentials";
	}


	/**
	 * @param title
	 * @param message
	 */
	public InvalidUserException(String title, String message) {
		super();
		this.title = Objects.requireNonNull(title, "Title cannot be null");
		this.message = Objects.requireNonNull(message, "Error message cannot be null");
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("InvalidUserException [title=%s, message=%s]", title, message);
	}
	
}
