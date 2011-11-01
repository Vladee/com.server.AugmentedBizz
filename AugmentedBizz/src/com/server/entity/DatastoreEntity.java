package com.server.entity;

import javax.persistence.Id;


/**
 * Abstract entity definition for datastore and transfer entities
 * @author Vladi
 *
 */
public abstract class DatastoreEntity {
	
	@Id
	protected Long id;
	
	/**
	 * Objectify needs a standard constructor
	 */
	protected DatastoreEntity()
	{
	}
	
	protected DatastoreEntity(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	
}
