import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import acm.util.ErrorException;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDatabase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDatabase(String filename) {
		BufferedReader rd = createReader(filename);
		try{
		while(true) {
			String line = rd.readLine();
			if (line == null) break;
			String[] splitLine = splitLine(line);
			database.put(splitLine[0], splitLine[1]);
			}
		} catch (IOException ex) {
			throw new ErrorException("database problem");
		}
	}
	
	private String[] splitLine(String line) {
		/*
		 * [0] name
		 * [1] ranks
		 */
		
		String[] splitLine = new String[2];
		int numLettersName = 0;
		while (line.charAt(numLettersName) != ' ') {numLettersName++;}
		splitLine[0] = line.substring(0, numLettersName);
		splitLine[1] = line.substring(numLettersName);
		return splitLine;
	}
	
	
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		if(database.containsKey(name)) {
			return new NameSurferEntry(name + database.get(name));
		}
		else return null;
	}
	
	
	private static BufferedReader createReader (String file) {
		BufferedReader rd;
		try{
			rd = new BufferedReader( new FileReader (file));
			} catch (IOException ex) {
				throw new ErrorException("file not found");
				}
		return rd;
	}
	private HashMap<String,String> database = new HashMap<String,String>();
}

