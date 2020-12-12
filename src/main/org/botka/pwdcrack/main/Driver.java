/*
 * File name:  Driver.java
 *
 * Programmer : Jake Botka
 *
 * Date: Mar 5, 2020
 *
 * 
 */
package main.org.botka.pwdcrack.main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import main.org.botka.pwdcrack.Hash;
import main.org.botka.pwdcrack.Util;

/**
 * Main Driver for program.
 *
 * @author Jake Botka
 *
 */
public class Driver {

	private final static int MAX_LENGTH = 35;

	public volatile static long count = 0;
	public static long timeStart = System.currentTimeMillis();
	public static volatile boolean stop = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String hash = "";
		if (args != null && args.length > 0) {
			System.out.println("args:\n");
			for (String s : args) {
				System.out.println("\n" + s);
			}
		} else {
			System.out.println("Enter Hash or password:");
			Scanner scan = new Scanner(System.in);
			hash = scan.nextLine();
			scan.close();
			if (hash.getBytes().length == 64) {
				try {
					hash = Hash.toHexString(Hash.getSHA(hash));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		}

		if (hash.length() > 0) {
			char[] alpha = null;
			String z = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
			alpha = z.toCharArray();
			short length = 1;
			String result = null;
			while (result == null) {
				for (short i = length; i > 0; i--) {
					result = Cracker.crack(alpha, hash, i);
					System.out.println("Found password: " + String.valueOf(result));
					if (result != null) {
						break;
					}
				}
				length ++;
			} 
			
		}

	}

	/**
	 * 
	 * @author Jake Botka
	 *
	 */
	public static class Cracker implements Runnable {
		
		private final char[] SUPPORTED_CHARS;
		private final String HASH;
		private final short LENGTH;
		
		public Cracker(char[] supportedChars, String hash, short length) {
			this.SUPPORTED_CHARS = supportedChars;
			this.HASH = hash;
			this.LENGTH = length;
		}
		
		/**
		 * 
		 * 
		 */
		@Override
		public void run() {
			crack(SUPPORTED_CHARS,HASH,LENGTH);
		}
		/**
		 * Cracks password.
		 * 
		 * @param chars
		 */
		public static String crack(char[] chars, String hash, short length) {
			boolean failFlag = false;
			if (hash == null) {
				failFlag = true;
			}
			if (!failFlag) {
				final long characterCount = chars.length;
				String password = "";
				while (true) {
					int[] arr = new int[length];
					for (int z = 0; z < length; z++) {
						arr[z] = 0;
					}
					
					int pointer = length - 1;
					boolean skip = false;
					while (true) {
						password = generatePassword(chars, length, arr);
						if (password != null) {
							if (evaluatePassword(password, hash)) {
								//System.out.println("Found password: " + password);
								return password;
							}
						}
						if (completedLength(arr, chars) == true) {
							skip = true;
							break;
						}
						while (!skip) {
							if (pointer < 0) {
								pointer = 0;
							}
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

						pointer = length - 1;
					}

					if (skip) {
						break;
					}
					
					
				}
			}
			return null;
			// implement timeout
		}

		
	}

	/**
	 * Generates a password bassed of the data provided through the methods
	 * parameters.
	 * 
	 * @param chars         Characters array supported.
	 * @param currentLength Current length of the password to generated.
	 * @param arr           Indexes to the char array to generate password.
	 * @return Generated password.
	 */
	public static String generatePassword(char[] chars, int currentLength, int[] arr) {
		String password = "";
		for (int i = 0; i < currentLength; i++) {
			password += chars[arr[i]];
		}
		count++;
		//Driver.safePrintln(password + "\tCount : " + count);
		return password;
	}

	/**
	 * 
	 * @param pwd  Password.
	 * @param hash Hash.
	 * @return
	 */
	public static boolean evaluatePassword(String pwd, String hash) {
		if (testPassword(pwd, hash)) {
			long timeEnd = System.currentTimeMillis() - timeStart;
			safePrintln("Seconds : " + Double.toString((double) timeEnd / 1000));
			return true;
		}
		return false;
	}

	/**
	 * Test password against the hash.
	 * 
	 * @param pwd  Password
	 * @param hash Hash.
	 * @return True if the hashed password matches the provided hash.
	 */
	public static boolean testPassword(String pwd, String hash) {
		String thePassword = pwd;
		try {
			if (Hash.toHexString(Hash.getSHA(pwd)).equals(hash)) {
				System.out.println("\nPassword Found : " + thePassword + "\nUsing this hash: "
						+ Hash.toHexString(Hash.getSHA(pwd)));
				return true;
			} else {
				if (pwd.equals(hash)) {
					System.out.println("\nPassword Found : " + pwd);
					System.out.println(Hash.toHexString(Hash.getSHA(pwd)));
					return true;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param arr
	 * @param chars
	 * @return
	 */
	public static boolean completedLength(int[] arr, char[] chars) {
		return arr != null && chars != null ? arr[0] == chars.length - 1 : false;
	}

	/**
	 * 
	 * @param s
	 */
	public static void unsafePintLn(String s) {
		System.out.println("\n" + s);
	}

	/**
	 * 
	 * @param s
	 */
	public static void safePrintln(String s) {
		synchronized (System.out) {
			System.out.println(s);
		}
	}

}
