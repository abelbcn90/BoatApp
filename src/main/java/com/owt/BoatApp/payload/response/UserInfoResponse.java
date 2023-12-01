/**
 * 
 */
package com.owt.BoatApp.payload.response;

/**
 * @author abelb
 *
 */
public class UserInfoResponse {
	private Long id;
	private String username;
	private String email;
	
	/**
	 * 
	 * @param id
	 * @param username
	 * @param email
	 */
	public UserInfoResponse(final Long id, final String username, final String email) {
		this.id = id;
		this.username = username;
		this.email = email;
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
}
