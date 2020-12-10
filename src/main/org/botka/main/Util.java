/**
 * File Name: Util.java
 * Programmer: Jake Botka
 * Date Created: Dec 10, 2020
 *
 */
package main.org.botka.main;

/**
 * @author Jake Botka
 *
 */
public class Util {

	public static boolean checkNotNull(Object object) {
		return object != null;
	}
	
	public static void checkNullAndThrow(Object object) {
		if (!checkNotNull(object)) {
			throw new NullPointerException();
		}
	}
}
