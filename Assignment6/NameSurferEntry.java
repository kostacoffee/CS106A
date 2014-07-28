/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		this.line = line + " ";
		// You fill this in //
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		int numLettersName = 0;
		while (line.charAt(numLettersName) != ' ') {numLettersName++;}
		String name = line.substring(0, numLettersName);
		
		// You need to turn this stub into a real implementation //
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		String allRanks;
		allRanks = line.substring(getName().length()+1);
		searchForRanks(allRanks);
		
		// You need to turn this stub into a real implementation //
		if (decadeRanks[decade] == 0) return 1000;
		else return decadeRanks[decade];
		
	}
	
	private void searchForRanks (String ranks) {
		int i;
		int rankPosition = 0;
		int decade = 0;
		for (i = 0; i < ranks.length(); i++) {
			if (ranks.charAt(i) == ' ') {
				decadeRanks[decade] = Integer.parseInt(ranks.substring(rankPosition, i));
				i++;
				rankPosition = i;
				decade++;		
			}
		}

		
		
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		String stringedLine;
		
		stringedLine = getName();
		stringedLine += " [";
		int i;
		for (i = 0; i < NDECADES; i++) {
			stringedLine += getRank(i) + " ";
		}
		stringedLine = stringedLine.substring(0, stringedLine.length()-1);
		stringedLine += "]";
		
		// You need to turn this stub into a real implementation //
		return stringedLine;
	}
	
	/* ivars */
	private String line;
	private int[] decadeRanks = new int[NDECADES];
	
	
}

