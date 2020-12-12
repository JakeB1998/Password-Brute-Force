/**
 * File Name: Command.java
 * Programmer: Jake Botka
 * Date Created: Dec 12, 2020
 *
 */
package main.org.botka.pwdcrack.commandln;

/**
 * Base class for command identifiers. Can commonly be used for command line identifiers.
 * @author Jake Botka
 *
 */
public class CommandIdentifier {

	private String mCommand;
	private String mCommandDescription;
	
	/**
	 * Default constrcutor.
	 */
	public CommandIdentifier() {
		this.mCommand = null;
		this.mCommandDescription = null;
	}

	/**
	 * 
	 * @param command
	 */
	public CommandIdentifier(String command) {
		this();
		this.mCommand = command;
	}
	
	public CommandIdentifier(String command, String commandDesctiption) {
		this(command);
		this.mCommandDescription = commandDesctiption;
	}
	
	/**
	 * 
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof String) {
			String value = (String )obj;
			return this.mCommand.equals(value);
		}
			
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCommand() {
		return this.mCommand;
	}
	
	/**
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.mCommand = command;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getCommandDesction() {
		return null;
	}
	
	/**
	 * 
	 * @param commandDescription
	 */
	public void setCommandDesctiption(String commandDescription) {
		this.mCommandDescription = commandDescription;
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return "Command [mCommand=" + mCommand + ", mCommandDescription=" + mCommandDescription + "]";
	}
	
	/**
	 * Builder class
	 * @author Jake Botka
	 *
	 */
	public static class Builder {
		
	}

}
