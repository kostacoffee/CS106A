/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;


public class Pyramid extends GraphicsProgram {
//Static Variables	{
	private static final long serialVersionUID = 1L;

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 25;
//END HERE	}
	
	public void run() {
		for (int i = 0; i<BRICKS_IN_BASE; i++) {
			pyramidBuild(i);
		}

	}
	private void pyramidBuild (int line) {
		//Function used for every line of Pyramid
		int x = (getWidth() - BRICK_WIDTH*BRICKS_IN_BASE)/2; // Starting point for bricks (x - coordinates)
		int y = getHeight()- BRICK_HEIGHT; // Starting poing for bricks (y - coordinates
		
		int moveAcrossFactor = (BRICK_WIDTH/2)*line; // How much the bricks must move by x axis when ascending to next row
		int moveUpFactor = BRICK_HEIGHT*line; // How much the bricks must move by y axis when ascending to next row
		
		for (int i = 0; i<BRICKS_IN_BASE-line; i++) {
			add(new GRect(x + i*BRICK_WIDTH + moveAcrossFactor, y - moveUpFactor, BRICK_WIDTH, BRICK_HEIGHT));
		}
	}
}

