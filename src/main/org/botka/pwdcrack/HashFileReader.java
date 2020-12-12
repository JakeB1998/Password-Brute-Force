/**
 * File Name: HashFileReader.java
 * Programmer: Jake Botka
 * Date Created: Dec 10, 2020
 *
 */
package main.org.botka.pwdcrack;

import java.io.File;
import java.io.FileNotFoundException;

import main.org.botka.pwdcrack.exceptions.FilePermissionsException;

/**
 * @author Jake Botka
 *
 */
public class HashFileReader {

	private File mFile;
	/**
	 * Default constructor.
	 * Private.
	 */
	private HashFileReader() {
		this.mFile = null;
	}
	
	public HashFileReader(File file) {
		this();
		this.mFile = file;
	}
	
	public String readHash() throws FileNotFoundException, FilePermissionsException {
		if (Util.checkNotNull(this.mFile)) {
			if (this.mFile.exists()) {
				if (this.mFile.canRead()) {
					String hash = "";
					//check if hash is supported
				} else {
					throw new FilePermissionsException("Does not have this.mFile permission: Read permission");
				}
			} else {
				throw new FileNotFoundException();
			}
		} 
		return null;
	}
}
