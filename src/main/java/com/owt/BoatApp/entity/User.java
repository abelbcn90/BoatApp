/**
 * 
 */
package com.owt.BoatApp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author abelb
 *
 */
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Username is mandatory")
	@Size(max = 30)
	private String username;

	@NotBlank(message = "Email is mandatory")
	@Size(max = 120)
	private String email;

	@NotBlank(message = "Password is mandatory")
	@Size(max = 120)
	private String password;

	/**
	 * 
	 */
	public User() {
		this.username = "";
		this.email = "";
		this.password = "";
	}

	/**
	 * @param username
	 * @param email
	 * @param password
	 */
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 */
//	@Override
	public String toString() {
		return "User{" + "id=" + id + ", username=" + username + ", email=" + email + '}';
	}
}
