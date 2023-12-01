/**
 * 
 */
package com.owt.BoatApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

/**
 * @author abelb
 *
 */
@Entity
public class Boat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Name is mandatory")
	private String name;

	@NotBlank(message = "Description is mandatory")
	private String description;

	/**
	 * 
	 */
	public Boat() {
		this.name = "";
		this.description = "";
	}

	/**
	 * @param name
	 * @param description
	 */
	public Boat(final String name, final String description) {
		this.name = name;
		this.description = description;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * 
	 */
//	@Override
	public String toString() {
		return "Boat{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
	}
}
