/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;

/**
 * A standard breakout game that consists of 100 bricks laid out in 10 bricks per row. 
 * The aim is for the player to use the paddle, that is tracked by the mouse on the x-axis, to
 * reflect the ball that is fired upon it and destroy all the blocks using it.
 * 
 * To start, the player simply has to left click the mouse.
 * 
 * @author Kosta
 * 
 */

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	
	public void run() {
		setup();
		addMouseListeners();
		turnCount = NTURNS + 1;
		play();
	}
	/** creates the playing field, the paddle and the set of bricks required for the game */
	public void setup () {
		
		new Dimension(WIDTH, HEIGHT);
		brickSet();
		paddle();
	}
	
	/** at first, creates a label that tells the user the click the mouse button to play
	 * afterwards, removes that label, and creates a fully functioning ball that is used to break the bricks
	 */
	public void play () {
		while (turnCount > 0 && brickNum > 0) { 
			tryLabel = new GLabel ("Click to play. You have " + turnCount + " tr" + (turnCount == 1 ? "y" : "ies") + " remaining");
			add(tryLabel,getWidth()/2 - tryLabel.getWidth()/2, getHeight()/2 - tryLabel.getAscent()/2);
			waitForClick();
			remove(tryLabel);
			makeBall();
			}
		if (turnCount == 0) {
			lostLabel = new GLabel ("You lost.");
			add(lostLabel,  getWidth()/2 - lostLabel.getWidth()/2, getHeight()/2 - lostLabel.getAscent()/2);
		}
	}
	
	/** Creates a ball that is used to destroy the bricks in the game */
	public void makeBall () {
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = 3.0;
		
		ball = new GOval (BALL_RADIUS, BALL_RADIUS);
		ball.setFilled(true);
		ball.setColor(Color.BLACK);
		add(ball, getWidth()/2 - BALL_RADIUS, getHeight()/2 - BALL_RADIUS);
		
		while (true) {
			ball.move(vx, vy);
			checkWallCollision();
			checkObjectCollision();
			pause(10);	
		}
	}
	
	/** Checks for collisions in any of the 4 walls.
	 * Note: if the ball collides with the bottom wall, then "anotherTry()" method is called
	 */
	public void checkWallCollision () {
		if (ball.getX()  <= 0 || ball.getX() + BALL_RADIUS >= getWidth()) {
			vx = -vx;
		}
		if (ball.getY() <= 0) {
			vy = -vy;
		}
		if ( ball.getY() + BALL_RADIUS >= getHeight()){
			anotherTry();
		}
	}
	/** Checks if all the bricks have been eliminated from the playing field.
	 * When that happens, removes the ball and adds the label exclaiming to the user that he/she won.
	 */
	public void checkWin () {
		if (brickNum == 0) {
			remove(ball);
			winLabel = new GLabel ("YOU WON!!!!!");
			add(winLabel, getWidth()/2 - winLabel.getWidth()/2, getHeight()/2 - winLabel.getAscent()/2);
		}
	}
	
	/** Reduces the amount of tries left by 1 every time it is envoked
	 * then invokes "play()" to continue playing
	 */
	public void anotherTry () {
			turnCount--;
			remove(ball);
			remove(tryLabel);
			play();
			
		}
	/** Checks for the collisions that the ball makes with other objects (paddle and bricks)
	 * if colliding with paddle, then the vertical velocity is reversed (the ball is reflected)
	 * otherwise, the ball will be checked for collision with bricks, at which point
	 * the ball will be reflected again, however the brick that was touched will be removed
	 * also, the brick count will be decremented by 1 and
	 * a check for 0 bricks (checkWin()) will be called
	 * 
	 * @see getCollidingObject()
	 */
	public void checkObjectCollision () {
		
		if (getCollidingObject() == paddle) {
			vy = -vy;
		}
		else if (getCollidingObject() !=paddle && getCollidingObject() != null) {
			vy = -vy;
			remove(getCollidingObject());
			brickNum--;
			checkWin();
		}
	}
	/** 4 points of possible collision are initialised.
	 * These are located on the corners of the bounding square of the ball.
	 * Then every object is checked one after the other for collision,
	 * if no collisions present, then the return is null,
	 * otherwise, the object that the ball collided with is retured
	 */
	public GObject getCollidingObject () {
		gobj1 = getElementAt(ball.getX(),ball.getY()); // top left corner
		gobj2 = getElementAt(ball.getX() + 2* BALL_RADIUS,ball.getY()); // top right corner
		gobj3 = getElementAt(ball.getX(),ball.getY() + BALL_RADIUS); // bottom left corner
		gobj4 = getElementAt(ball.getX() + BALL_RADIUS,ball.getY() + BALL_RADIUS); // bottom right corner
		
		if (gobj1 == null) {
			if (gobj2 == null) {
				if (gobj3 == null) {
					if (gobj4 == null) {
						return null;
					}
					else return gobj4;
				}
				else return gobj3;
			}
			else return gobj2;
		}
		else return gobj1;
	}
	
	/** Creates the paddle that follows the x-axis of the mouse and is fixed
	 * on the y-coordinate seen in PADDLE_Y_OFFSET
	 */
	public void paddle() {
		lastx = getWidth()/2 - PADDLE_WIDTH/2;
		double y = getHeight()- PADDLE_Y_OFFSET;
		
		paddle = new GRect (lastx, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setColor(Color.BLACK);
		add(paddle);
	}

	/** Called to make the paddle follow the x-axis of the mouse
	 * includes the fixes for the paddle going off the screen
	 */
	public void mouseMoved (MouseEvent e) {
		double center = e.getX() - PADDLE_WIDTH/2;
		
		paddle.move(center - lastx,0);
		lastx = center;
		if (center + PADDLE_WIDTH >= WIDTH) {
			paddle.move(WIDTH - center - PADDLE_WIDTH,0);
			lastx = WIDTH - PADDLE_WIDTH;
		}
		else if (center <= 0) {
			paddle.move(-center,0);
			lastx = 0;
		}
	}
	
	/** Creates the correct amount of columns of bricks
	 * @see brickRow(int numRows)
	 */
	private void brickSet() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			brickRow(i);
		}
	}
	
	/** Creates a column of bricks.
	 * uses a loop that adds a new brick to the canvas from top to bottom
	 * taking into the account the initial y-offest that is seen in BRICK_Y_OFFSET
	 */
	public void brickRow(int numRows) {
		for (int i = 1; i < NBRICKS_PER_ROW + 1; i++) {
			brick = new GRect(BRICK_WIDTH, BRICK_HEIGHT);
			brick.setFilled(true);
			colorIn(i, brick);
			add(brick, (numRows + 0.5)*BRICK_SEP + numRows*BRICK_WIDTH, i*BRICK_SEP + i*BRICK_HEIGHT + BRICK_Y_OFFSET);
			}
		}
	/** Colours in the bricks column by column.
	 * The top 2 are red, the next 2 are orange,
	 * next are yellow, a green set which is followed by cyan.
	 */
	private void colorIn(int n, GRect name) {
		if (n == 1 || n == 2) {
			name.setColor(Color.RED);
		}
		else if (n == 3 || n == 4) {
			name.setColor(Color.ORANGE);
		}
		else if (n == 5 || n == 6) {
			name.setColor(Color.YELLOW);
		}
		else if (n == 7 || n == 8) {
			name.setColor(Color.GREEN);
		}
		else if (n == 9 || n == 10) {
			name.setColor(Color.CYAN);
		}
	}
	/** instance of RandomGenerator requred for the calculation of vx */
	private RandomGenerator rgen = RandomGenerator.getInstance();
	/** initialisation for an integer that counts the amount of tries a player has left */
	private int turnCount;
	/** calculates the total number of bricks */
	private int brickNum = NBRICK_ROWS * NBRICKS_PER_ROW;
	/** initialises the three labels that are used to inform the player about their progress */
	private GLabel lostLabel,tryLabel, winLabel;
	/** initialises the GRect used for creation of the set of bricks */
	private GRect brick;
	/** initialises the GRect used for the creation of the paddle */
	private GRect paddle;
	/** initialises the GOval used for creating the ball */
	private GOval ball;
	/** initialises the 4 analysis objects that are used in getCollidingObject() */
	private GObject gobj1, gobj2, gobj3, gobj4;
	/** initialises the x-coordinate used for tracking the mouse on its x-axis */
	private double lastx;
	/** initialises the velocity variables used
	 *  in controlling the direction and speed at which the ball moves 
	 */
	private double vx, vy;
}
