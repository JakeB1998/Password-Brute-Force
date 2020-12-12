/**
 * File Name: CommandExecutionMap.java
 * Programmer: Jake Botka
 * Date Created: Dec 12, 2020
 *
 */
package main.org.botka.pwdcrack.commandln;

import java.util.HashMap;

/**
 * @author Jake Botka
 *
 */
public class CommandExecutionMap {

	private HashMap<CommandIdentifier, CommandExecuteDelegate> mCommandsMap;
	
	/**
	 * 
	 */
	public CommandExecutionMap() {
		this.mCommandsMap = new HashMap<>();
	}
	
	public CommandExecutionMap(int initialCapacity) {
		this.mCommandsMap = new HashMap<>(initialCapacity);
	}
	
	public CommandExecuteDelegate addCommand(CommandIdentifier command, CommandExecuteDelegate commandExecutor) {
		if (!this.mCommandsMap.containsKey(command)) {
			return this.mCommandsMap.put(command, commandExecutor);
		}
		return null;
	}

}
