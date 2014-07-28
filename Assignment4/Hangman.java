/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;


public class Hangman extends ConsoleProgram {
	
	private static final int NUM_LIVES = 8;
	
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
		}

    public void run() {
    	
		/** prints a welcome message */
    	println("Greetings, you are playing Hangman!");
    	
    	
    	/** Sets up the game with a new word and prints its dashed counterpart
    	 * also asks the user for their first guess and assigns it to the variable with the same name */
    	setup();
    	
    	/** initiates the game.
    	 * asks for player's guess, error checks it, assigns a new variable to a character of the same guess
    	 * verifies its existence within the hidden word
    	 * 
    	 */
    	
    	play();
    	sendMessage();
    	
    
	}
    
    private void sendMessage () {
    	
    	if (numLives == 0) {
    		println ("Sadly, you could not get the word");
    		println ("The word was " + ranWord);
    	}
    	else if (dispWord.equals(ranWord) ) {
    		println("YAY!!!! YOU GOT THE WHOLE WORD!!!!");
    		println("And you stil have " + numLives + " lives left!");
    	}
    }
    
    
    private void play () {
    	while (!dispWord.equals(ranWord) && numLives > 0){
    	canvas.displayWord(dispWord);
    		
    	println("You have " + numLives + " lives left");	
    	
    	guess = readLine("Please input your guess in a form of 1 letter: ");
    	
	    	if (guess.length() == 1) {
	    		
	    		guess = guess.toUpperCase();
	    		guessLetter = guess.charAt(0);
	    		
	    		if (Character.isLetter(guessLetter)) {
	    			
	    			guessLetter = Character.toUpperCase(guessLetter);
	    			
	    			if (ranWord.indexOf(guessLetter) != -1) {
	    			
	    			for (int i = 0; i < ranWord.length(); i++) {
	    				
	    				if (ranWord.indexOf(guess, i) == i) {
	    					
	    					updateDispWord(i);
	    					println ("Correct! Letter " + guess + " is in the word");
	    					println("This is how your word looks like now " + dispWord);
	    					}
	    				
	    				}
	    				play();
	    			}
	    			else wrongLetter();
	    		}
	    		else {
	    			println ("Please input a letter as your guess.");
	    			play();
	    		}
	    		
	    	}
	    	else {
	    		println("Please input only 1 letter.");
	    		play();
	    	}
    	}
    }
    

    
    private void wrongLetter () {
    	
    	println("Sorry, the letter " + guess + " is not in this word.");
		numLives--;
		canvas.noteIncorrectGuess(guessLetter);
		play();
    	
    }
    
    private void updateDispWord (int index) {
    	if (index != ranWord.length()) {
    	dispWord = dispWord.substring(0,index) + guess + dispWord.substring(index+1);
    	}
    	else dispWord = dispWord.substring(0,index) + guess;
    	
    	canvas.displayWord(dispWord);
    }
    
    private void setup () {

    	dashDispWord();
    	println("this is how your word looks like now " + dispWord);
    	canvas.reset();
    }
    
    private void dashDispWord () {
    	
    	for (int i = 0; i < ranWord.length(); i++) {
    		dispWord += '-';
    	}
    	
    }
   
    private int numLives = NUM_LIVES;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    int ranWordIndex = rgen.nextInt(0, HangmanLexicon.getWordCount()-1);
    private String ranWord = HangmanLexicon.getWord(ranWordIndex);
    private String dispWord = "";
    private String guess;
    private char guessLetter;
    private HangmanCanvas canvas;
   
   
}