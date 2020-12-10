/**
 * File Name: Hash.java
 * Programmer: Jake Botka
 * Date Created: Dec 10, 2020
 *
 */
package main.org.botka.main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jake Botka
 *
 */
public class Hash {

	private static final String[] SUPPORTED_HASHES = {};

	private String mHash;

	/**
	 * 
	 * @return
	 */
	public static String[] getSupportedHashes() {
		return SUPPORTED_HASHES;
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

	/**
	 * 
	 */
	public Hash() {
		this.mHash = null;
	}

	public Hash(String hash) {
		this.mHash = hash;
	}

	public boolean isHashSupported() {
		return this.isHashSupported(this.mHash);
	}

	public boolean isHashSupported(String hash) {
		if (Util.checkNotNull(hash)) {

		}
		return false;
	}

	public boolean isEncodedBase64() {
		return this.isEncodedBase64(this.mHash);
	}

	public boolean isEncodedBase64(String hash) {
		return false;
	}

	public String getHash() {
		return this.mHash;
	}

}
