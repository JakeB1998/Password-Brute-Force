/**
 * File Name: CommandExecuteDelegate.java
 * Programmer: Jake Botka
 * Date Created: Dec 12, 2020
 *
 */
package main.org.botka.pwdcrack.commandln;

/**
 * Executes a specific command.
 * Use with command map.
 * @author Jake Botka
 *
 */
public interface CommandExecuteDelegate {

	public void executeCommand(CommandIdentifier commandIdentifier);
}
