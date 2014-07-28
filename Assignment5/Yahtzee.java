/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.lang.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		int i;
		for (i = 0; i < N_SCORING_CATEGORIES * nPlayers; i++) {
			player = (i % nPlayers) + 1; // switches to the next player
			int j;
			setupRoll();
			for (j = 0; j < N_REROLLS; j++) reRoll();
			scoreTurn();
		}
		announceWinner();
	}
	
	private void announceWinner () {
		int i;
		int playerWithMostScore = 0;
		for (i = 1; i < nPlayers; i++) {
			if (total[i] > total[i - 1]) {
				playerWithMostScore = i;
			} 
		}
		display.printMessage("Player " + (playerWithMostScore+1) + " - "+
		playerNames[playerWithMostScore] + ". YOU WIN!!!! ");
		
	}
	
	private void scoreTurn() {
		int category = display.waitForPlayerToSelectCategory();
		
		if (takenCategories[player - 1][category-1] == 1) {
			display.printMessage("category taken, please choose another one");
			scoreTurn();
			return;
		}
		
		takenCategories[player - 1][category-1] = 1;
		int score = 0;
		if (category >= ONES && category <= SIXES) {
			int i;
			for (i=0; i < N_DICE; i++) {
				if (dice[i] == category) score += dice[i];
				
			}
			upperScore[player-1] += score;
			total[player-1] += upperScore[player-1];
			display.updateScorecard(UPPER_SCORE, player, upperScore[player-1]);
			if (upperScore[player-1] >= 63) {
				display.updateScorecard(UPPER_BONUS, player, UPPER_BONUS_SCORE);
			}
		}
		
		else switch (category) {
		case THREE_OF_A_KIND:	score = checkCategory(THREE_OF_A_KIND);
								break;
		case FOUR_OF_A_KIND:	score = checkCategory(FOUR_OF_A_KIND);
								break;
		case YAHTZEE: 			score = checkCategory(YAHTZEE);
								break;
		case FULL_HOUSE:		score = checkCategory(FULL_HOUSE);
								break;
		case SMALL_STRAIGHT:	score = checkCategory(SMALL_STRAIGHT);
								break;
		case LARGE_STRAIGHT:	score = checkCategory(LARGE_STRAIGHT);
								break;	
		case CHANCE:			score = checkCategory(CHANCE);
		}
		display.updateScorecard(category, player, score);
		display.updateScorecard(TOTAL, player, total[player-1]);
		
		
	}
	
	private int checkCategory (int category) {
		int score = 0;
		int [] trackers = getTrackers();
		
		if (category == LARGE_STRAIGHT) {
			if (trackers[0] == 5) {
				score = 40;
			}
		}
		
		else if (category == SMALL_STRAIGHT) {
			if (trackers[0] == 5 || (trackers[0] == 4 && trackers[1] == 2)) {
				score = 30;
			}
		}
		else if (category == THREE_OF_A_KIND) {
			if (trackers[1] >= 3) {
				int i;
				for (i = 0; i < N_DICE; i++) {
					score += dice[i];
				}
			}
		}
		else if (category == FOUR_OF_A_KIND) {
			
			if ((trackers[1] == 4 && (trackers[2] == 0 || trackers[2] == 4)) || trackers[1] == 5) {
				int i;
				for (i = 0; i < N_DICE; i++) {
					score += dice[i];
				}
			}
		}
		
		else if (category == FULL_HOUSE) {
			if (trackers[0] == 2 && trackers[1] == 4 && (trackers[2] == 1 || trackers[2] == 2)){
				score = 25;
			} 
		}
		else if (category == YAHTZEE) {
			if (trackers[0] == 1) {
				score = 50;
			}
		}
		
		else if (category == CHANCE) {
			int i;
			for (i = 0; i < N_DICE; i++) {
				score += dice[i];
			}

		}
			

		lowerScore[player-1] += score;
		total[player-1] +=lowerScore[player-1];
		display.updateScorecard(LOWER_SCORE, player, lowerScore[player-1]);
		return score;
	}

	
	private int[] getTrackers () {
		int [] tempArr = dice;
		Arrays.sort(tempArr);
		/**
		 * trackers:
		 * [0] how many different values
		 * [1] how many times a value repeated itself
		 * [2] the last index at which the value skips to a higher one.
		 */
		int [] trackers = new int [3];
		trackers[1] = 1;
		int i;
		int j;
		for (i = 1; i < 7; i++) {
			if (Arrays.binarySearch(tempArr, i) >-1) trackers[0]++;
		}
		for (j = 0; j < N_DICE; j++) {
			if (j > 0) {
				if (tempArr[j] == tempArr[j-1]) trackers[1]++;
				else trackers[2] = j - 1;
			}
			
		}
		
		return trackers;
		
	}
	
	private void reRoll() {
		display.waitForPlayerToSelectDice();
		
		int i;
		for (i = 0; i < N_DICE; i++) {
			
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
				
			}
			
		}
		display.displayDice(dice);
	}
	
	private void setupRoll () {
		int i;
		display.waitForPlayerToClickRoll(player);
		for (i = 0; i < N_DICE; i++) {
		dice[i] = rgen.nextInt(1, 6);
		}
		display.displayDice(dice);
		 //player number stub
	}
	/* Private instance variables */
	IODialog dialog = getDialog();
	private int nPlayers = dialog.readInt("Enter number of players");
	private int[][] takenCategories = new int [nPlayers] [N_CATEGORIES];
	private int[] total = new int[nPlayers];
	private int[] upperScore = new int[nPlayers];
	private int[] lowerScore = new int[nPlayers];
	private int player;
	private int[] dice = new int[N_DICE];
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
