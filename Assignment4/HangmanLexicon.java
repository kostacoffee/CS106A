/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;


public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
	public static int getWordCount() {
		
		int wordCount = 0;

		BufferedReader lineCounter = createReader();
		
		try{
		while (true) {
			String tester = lineCounter.readLine();
			if (tester == null) break;
			wordCount++;
			}
		lineCounter.close();
		} catch (IOException ex){
			throw new ErrorException("Something's wrong with lines counted");
		}
		
		return wordCount;
		
	}

/** Returns the word at the specified index. */
	public static String getWord(int index) {
		BufferedReader lineReader = createReader();
		
		String word = "";
		
		try{
			for (int i = 0; i < index; i++) {
				word = lineReader.readLine(); 
				}
			lineReader.close();
			} catch (IOException ex) {
				throw new ErrorException("Something's wrong with the array.");
			}
			return word;
	}

	
	private static BufferedReader createReader () {
		BufferedReader rd;
		try{
			rd = new BufferedReader( new FileReader ("HangmanLexicon.txt"));
			} catch (IOException ex) {
				throw new ErrorException("file not found");
				}
		return rd;
	}
	
}
