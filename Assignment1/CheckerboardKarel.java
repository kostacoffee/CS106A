/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
	
		checkerRow();
			while (leftIsClear()) {
				nextRow_West();
				checkerRow();
			
			if (rightIsClear()) {
				nextRow_East();
				checkerRow();
			} else {
				turnAround();
			}
		}
		
		
	


		
		
	
	}
	
	private void nextRow_West() {
		turnLeft();
		move();
		turnLeft();
	}
	private void nextRow_East() {
		turnRight();
		move();
		turnRight();
	}
	
	private void checkerRow() {
		while (frontIsClear()) {
			putBeeper();
			skip();
		}
	}
	
	private void skip() {
			move();
		if (frontIsClear()) {
			move();
		}
	}
}
	



