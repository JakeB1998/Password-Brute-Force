/*
 * File name:  PasswordCrackDriver.java
 *
 * Programmer : Jake Botka
 * ULID: JMBOTKA
 *
 * Date: Mar 5, 2020
 *
 * Out Of Class Personal Program
 */
package Botka.Jake;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * <insert class description here>
 *
 * @author Jake Botka
 *
 */
public class PasswordCrackDriver {

	private static String password = "Avsf";
	private final static int MAX = 35;
	private volatile static int lengthA = 0;

	public volatile static boolean breakif = false;

	public static long timeStart = System.currentTimeMillis();

	private static int serverCount = 0;

	private static boolean ishash = false;

	public volatile static int count = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] alpha;
		String z = "abcdefghijklmnopqrstuvwxyz0123456789";
		alpha = z.toCharArray();
		crack(alpha);

	}

	public static String crack(char[] chars) {

		System.out.println("Enter Hash or password:");
		Scanner scan = new Scanner(System.in);
		password = scan.nextLine();

		if (password.getBytes().length == 64) {
			ishash = true;

		}

		// password = password.trim();

		String password = "";

		int[] arr = new int[10];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = 0;
		}

		int indexIncrease = 0;
		
		while (true) {

			if (arr[indexIncrease] < MAX) {
				if (lengthA < 2) {
					lengthA = 1;
				}
				arr[indexIncrease] += 1;
			} else {
				arr[indexIncrease] = 0;
				if (arr[indexIncrease + 1] < MAX) {

					if (lengthA < 3) {
						lengthA = 2;
					}
					arr[indexIncrease + 1] += 1;
				} else {
					arr[indexIncrease + 1] = 0;
					if (arr[indexIncrease + 2] < MAX) {
						if (lengthA < 4) {
							lengthA = 3;
						}
						arr[indexIncrease + 2] += 1;
					} else {
						if (lengthA < 5) {
							lengthA = 4;
						}
						arr[indexIncrease + 2] = 0;
						if (arr[indexIncrease + 3] < MAX) {
							arr[indexIncrease + 3] += 1;
						} else {
							if (lengthA < 6) {
								lengthA = 5;
							}
							arr[indexIncrease + 3] = 0;
							if (arr[indexIncrease + 4] < MAX) {
								arr[indexIncrease + 4] += 1;
							} else {
								arr[indexIncrease + 4] = 0;
								if (arr[indexIncrease + 5] < MAX) {
									lengthA = 6;
									arr[indexIncrease + 5] += 1;
								} else {
									arr[indexIncrease + 5] = 0;
									if (arr[indexIncrease + 6] < MAX) {
										lengthA = 7;
										arr[indexIncrease + 6] += 1;
									} else {
										arr[indexIncrease + 6] = 0;
										if (arr[indexIncrease + 7] < MAX) {
											lengthA = 8;
											arr[indexIncrease + 7] += 1;
										} else {
											arr[indexIncrease + 7] = 0;
											if (arr[indexIncrease + 8] < MAX) {
												lengthA = 9;
												arr[indexIncrease + 8] += 1;
											} else {
												arr[indexIncrease + 8] = 0;
												if (arr[indexIncrease + 9] < MAX) {
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

			xx(chars, arr);

			// xx(chars,arr);

			if (breakif) {
				break;
			}
		}
		password = "";
		return password;
	}

	public static void xx(char[] chars, int[] arr) {
		String password = "";
		for (int i = 0; i < lengthA; i++) {
			password += chars[arr[i]];
		}
		count++;
		System.out.println(password + "\tCount : " + count);

		if (test(password)) {
			long timeEnd = System.currentTimeMillis() - timeStart;
			safePrintln("Seconds : " + Double.toString((double) timeEnd / 1000));
			breakif = true;
		}
	}

	public static boolean test(String test) {
		String thePassword = test;
		try {
			if (ishash) {
				if (toHexString(getSHA(test)).equals(password)) {
					System.out.println(
							"\nPassword Found : " + thePassword + "\nUsing this hash: " + toHexString(getSHA(test)));
					return true;
				}
			} else {
				if (test.equals(password)) {
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
