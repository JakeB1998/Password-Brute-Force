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
	
	private volatile static int lengthA = 0;
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

		String password = "";
		int[] arr = new int[MAX_LENGTH];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}

		int indexIncrease = 0;
		
		while (true) {

			if (arr[indexIncrease] < MAX_LENGTH) {
				if (lengthA < 2) {
					lengthA = 1;
				}
				arr[indexIncrease] += 1;
			} else {
				arr[indexIncrease] = 0;
				if (arr[indexIncrease + 1] < MAX_LENGTH) {

					if (lengthA < 3) {
						lengthA = 2;
					}
					arr[indexIncrease + 1] += 1;
				} else {
					arr[indexIncrease + 1] = 0;
					if (arr[indexIncrease + 2] < MAX_LENGTH) {
						if (lengthA < 4) {
							lengthA = 3;
						}
						arr[indexIncrease + 2] += 1;
					} else {
						if (lengthA < 5) {
							lengthA = 4;
						}
						arr[indexIncrease + 2] = 0;
						if (arr[indexIncrease + 3] < MAX_LENGTH) {
							arr[indexIncrease + 3] += 1;
						} else {
							if (lengthA < 6) {
								lengthA = 5;
							}
							arr[indexIncrease + 3] = 0;
							if (arr[indexIncrease + 4] < MAX_LENGTH) {
								arr[indexIncrease + 4] += 1;
							} else {
								arr[indexIncrease + 4] = 0;
								if (arr[indexIncrease + 5] < MAX_LENGTH) {
									lengthA = 6;
									arr[indexIncrease + 5] += 1;
								} else {
									arr[indexIncrease + 5] = 0;
									if (arr[indexIncrease + 6] < MAX_LENGTH) {
										lengthA = 7;
										arr[indexIncrease + 6] += 1;
									} else {
										arr[indexIncrease + 6] = 0;
										if (arr[indexIncrease + 7] < MAX_LENGTH) {
											lengthA = 8;
											arr[indexIncrease + 7] += 1;
										} else {
											arr[indexIncrease + 7] = 0;
											if (arr[indexIncrease + 8] < MAX_LENGTH) {
												lengthA = 9;
												arr[indexIncrease + 8] += 1;
											} else {
												arr[indexIncrease + 8] = 0;
												if (arr[indexIncrease + 9] < MAX_LENGTH) {
													lengthA = 10;
													arr[indexIncrease + 9] += 1;
												} else {
													break;
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}

			/*
			 * if (serverCount < 100) { Thread t = new Thread(new Runnable() {
			 * 
			 * @Override public void run() { xx(chars,arr); serverCount--;
			 * 
			 * 
			 * }
			 * 
			 * }); t.start(); serverCount++; }
			 */

			password = generatePassword(chars, arr);
			if (password != null) {
				if (evaluatePassword(password, hash)) {
					break;
				}
			}
			
			
		}
		password = "";
		return password;
	}

	public static String generatePassword(char[] chars, int[] arr) {
		String password = "";
		for (int i = 0; i < lengthA; i++) {
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
				if (toHexString(getSHA(test)).equals(hash)) {
					System.out.println(
							"\nPassword Found : " + thePassword + "\nUsing this hash: " + toHexString(getSHA(test)));
					return true;
				}
			} else {
				if (test.equals(hash)) {
					System.out.println("\nPassword Found : " + test);
					System.out.println(toHexString(getSHA(test)));
					return true;
				}
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static byte[] getSHA(String bs) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		
		return md.digest(bs.getBytes(StandardCharsets.UTF_8));
	}

	public static String toHexString(byte[] hash) {
		// Convert byte array into signum representation
		BigInteger number = new BigInteger(1, hash);

		// Convert message digest into hex value
		StringBuilder hexString = new StringBuilder(number.toString(16));

		// Pad with leading zeros
		while (hexString.length() < 32) {
			hexString.insert(0, '0');
		}

		return hexString.toString();
	}

	public static void safePrintln(String s) {
		synchronized (System.out) {
			System.out.println(s);
		}
	}

}
