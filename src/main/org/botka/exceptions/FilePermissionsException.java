/**
 * File Name: FilePermissionsException.java
 * Programmer: Jake Botka
 * Date Created: Dec 10, 2020
 *
 */
package main.org.botka.exceptions;

/**
 * @author Jake Botka
 *
 */
public class FilePermissionsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8443833493987672137L;

	/**
	 * 
	 */
	public FilePermissionsException() {
		super();
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public FilePermissionsException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public FilePermissionsException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 */
	public FilePermissionsException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public FilePermissionsException(Throwable arg0) {
		super(arg0);
		
	}

}
