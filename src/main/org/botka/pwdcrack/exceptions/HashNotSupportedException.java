/**
 * File Name: HashNotSupportedException.java
 * Programmer: Jake Botka
 * Date Created: Dec 10, 2020
 *
 */
package main.org.botka.pwdcrack.exceptions;

/**
 * @author Jake Botka
 *
 */
public class HashNotSupportedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public HashNotSupportedException() {
		
	}

	/**
	 * @param message
	 */
	public HashNotSupportedException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public HashNotSupportedException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HashNotSupportedException(String message, Throwable cause) {
		super(message, cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public HashNotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
