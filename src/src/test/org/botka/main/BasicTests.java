/**
 * File Name: BasicTests.java
 * Programmer: Jake Botka
 * Date Created: Jan 18, 2021
 *
 */
package src.test.org.botka.main;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author Jake Botka
 *
 */
public class BasicTests {
	final StringBuilder strBuilder = new StringBuilder("");

	@Test
	public void testString() {
		//Profeciency Test
		String str = "";
		for (int i = 0; i < 1000; i++) {
			str += String.valueOf(i);
		}
		assertTrue(true);
		
	}
	
	@Test
	public void testStringBuilder() {
		//Profeciency Test
		
		for (int i = 0; i < 1000; i++) {
			strBuilder.append(String.valueOf(i));
		}
		
		assertTrue(true);
	}

}
