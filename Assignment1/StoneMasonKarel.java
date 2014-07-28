/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		
		faceNorth();
		
		BeeperFill();

		movetoNext();
		
		BeeperFill();
	
		movetoNext();
		
		BeeperFill();
		
		movetoNext();

		BeeperFill();
		
		BeeperCheck();
		
		
		

	}
//	Facing Functions {
	private void faceNorth() {
		while (notFacingNorth()) {
			turnLeft();
		}
	}
	
	private void faceSouth() {
		while (notFacingSouth()) {
			turnLeft();
		}
	}
	
	private void faceEast() {
		while (notFacingEast()) {
			turnLeft();
		}
	}
//	end here }

	private void BeeperFill(){
		while (frontIsClear()) {
			BeeperCheck();
			move();
			}	
		}
	
	private void contWalk() {
		while (frontIsClear()) {
			move();
		}
	}
	
	private void BeeperCheck() {
		if (noBeepersPresent()) {
			putBeeper();
		}
	}
	
	private void movetoNext () {
		BeeperCheck();
		faceEast();
		for (int i=0; i<4; i++){
			move();
		}
		faceNorth();
		contWalk();
		faceSouth();
	}
	
	
}
