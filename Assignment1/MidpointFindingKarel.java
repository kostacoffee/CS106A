/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		
		placeBeepersInLine();
		runToEndOfBeeperLine_1();
		pickSideBeepers();
		turnAround();
		move();
		putBeeper();
		
		
	}
	
	private void placeBeepersInLine() {
		putBeeper();
		while (frontIsClear()) {
			move();
			putBeeper();
		}
	}
	private void runToEndOfBeeperLine_1() {	
		turnAround();
			move();
			while (beepersPresent() && frontIsClear()) {
				move();
		}
			turnAround();
	}
	
	private void pickSideBeepers() {
		while (beepersPresent()) {
			runToEndOfBeeperLine();
			pickBeeper();
			if (frontIsBlocked()){
				turnAround();
			}
			move();
		}
	}
	
	private void runToEndOfBeeperLine() {
		while (frontIsClear() && beepersPresent()) {
			move();
		}
		if (noBeepersPresent()) {
			turnAround();
			move();
		}
		
	}
		

	
}


		
	
