/**
 * 
 */
package com.learn.oauth.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

/**
 * @author vkalyana
 *
 */
public class LogEvent {

	@Id
	private String id;
	
	private String entity;
	private String operation;
	private String errorMessage;
	private LocalDate updated;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}
	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
	 * @return the updated
	 */
	public LocalDate getUpdated() {
		return updated;
	}
	
	/**
	 * @param id
	 * @param entity
	 * @param operation
	 * @param errorMessage
	 * @param updated
	 */
	public LogEvent(String entity, String operation, String errorMessage) {
		super();
		this.entity = entity;
		this.operation = operation;
		this.errorMessage = errorMessage;
		this.updated = LocalDate.now();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("LogEvent [id=%s, entity=%s, operation=%s, errorMessage=%s, updated=%s]", id, entity,
				operation, errorMessage, updated);
	}
	
	
}
