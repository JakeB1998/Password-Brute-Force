/**
 * File Name: Hash.java
 * Programmer: Jake Botka
 * Date Created: Dec 10, 2020
 *
 */
package main.org.botka.main;

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
