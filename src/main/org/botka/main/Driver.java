/*
 * File name:  Driver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Mar 5, 2020
 *
 * 
 */
package main.org.botka.main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Main Driver for program.
 *
 * @author Jake Botka
 *
 */
public class Driver {

	private final static int MAX_LENGTH = 35;
	
	
	public volatile static int count = 0;
	
	public static long timeStart = System.currentTimeMillis();
	private static boolean ishash = false;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hash = "";
		if (args != null && args.length > 0) {
			//hande
			System.out.println("has args");
		}
		else {
			System.out.println("Enter Hash or password:");
			Scanner scan = new Scanner(System.in);
			hash = scan.nextLine();
			scan.close();
		}
		char[] alpha = null;
		String z = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
		alpha = z.toCharArray();
		crack(alpha, hash);

	}

	/**
	 * Cracks password
	 * @param chars
	 * @return
	 */
	public static String crack(char[] chars, String hash) {

		if (hash.getBytes().length == 64) {
			ishash = true;

		}
		final long characterCount = chars.length;
		String password = "";
		
		int indexIncrease = 0;
		int currentLength = 1;
		while (true) {
			int[] arr = new int[currentLength];
			for (int z = 0; z < currentLength; z++) {
				arr[z] = 0;
			}
			for (int i =currentLength; i > 0; i--) {
				int pointer = currentLength - 1;
				while(true) {
					password = generatePassword(chars, currentLength,arr);
					if (password != null) {
						if (evaluatePassword(password, hash)) {
							return password;
						}
					}
					if (i == 1 && completedLength(arr, chars) == true) {
						break;
					}
					while (true) {
						arr[pointer] += 1;
						int charIndex = arr[pointer];
						if (charIndex >= characterCount) {
							arr[pointer] = 0;
							pointer--;
						} else {
							arr[pointer] = charIndex;
							break;
						}
					}
					pointer = currentLength - 1;
						
				}
				
			}
			currentLength++;
		}
		
		//implement timeout
	}

	
	public static String generatePassword(char[] chars, int currentLength, int[] arr) {
		
		String password = ""; 
		for (int i = 0; i < currentLength; i++) {
			password += chars[arr[i]];
		}
		count++;
		System.out.println(password + "\tCount : " + count);
		return password;
	}
	
	public static boolean evaluatePassword(String pwd, String hash) {
		if (testPassword(pwd, hash)) {
			long timeEnd = System.currentTimeMillis() - timeStart;
			safePrintln("Seconds : " + Double.toString((double) timeEnd / 1000));
		
			return true;
		}
		return false;
	}

	public static boolean testPassword(String test, String hash) {
		String thePassword = test;
		try {
			if (ishash) {
				if (Hash.toHexString(Hash.getSHA(test)).equals(hash)) {
					System.out.println(
							"\nPassword Found : " + thePassword + "\nUsing this hash: " + Hash.toHexString(Hash.getSHA(test)));
					return true;
				}
			} else {
				if (test.equals(hash)) {
					System.out.println("\nPassword Found : " + test);
					System.out.println(Hash.toHexString(Hash.getSHA(test)));
					return true;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	public static boolean completedLength(int[] arr, char[] chars) {
		return arr != null && chars != null ? arr[0] == chars.length - 1 : false;
	}

	public static void safePrintln(String s) {
		synchronized (System.out) {
			System.out.println(s);
		}
	}

}
