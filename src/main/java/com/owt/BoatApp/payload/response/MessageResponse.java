/**
 * 
 */
package com.owt.BoatApp.payload.response;

/**
 * @author abelb
 *
 */
public class MessageResponse {
	private String message;

	/**
	 * 
	 * @param message
	 */
	public MessageResponse(final String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @param message
	 */
	public void setMessage(final String message) {
		this.message = message;
	}
}
