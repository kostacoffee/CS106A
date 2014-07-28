/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;





public class Target extends GraphicsProgram {	
	
	public static final double INNER_CIRCLE_RADIUS = 0.3;
	
	public static final double MID_CIRCLE_RADIUS = 0.65;
	
	public static final double OUTER_CIRCLE_RADIUS = 1.0;
	
	public void run() {
		
		GOval innerRed = new GOval (xOfOval(INNER_CIRCLE_RADIUS), yOfOval(INNER_CIRCLE_RADIUS),  inchToPixel(INNER_CIRCLE_RADIUS), inchToPixel(INNER_CIRCLE_RADIUS));
		innerRed.setColor(Color.RED);
		innerRed.setFilled(true);
		
		
		GOval midWhite = new GOval (xOfOval(MID_CIRCLE_RADIUS), yOfOval(MID_CIRCLE_RADIUS),  inchToPixel(MID_CIRCLE_RADIUS), inchToPixel(MID_CIRCLE_RADIUS));
		midWhite.setColor(Color.WHITE);
		midWhite.setFilled(true);
		
		
		GOval outerRed = new GOval (xOfOval(OUTER_CIRCLE_RADIUS), yOfOval(OUTER_CIRCLE_RADIUS),  inchToPixel(OUTER_CIRCLE_RADIUS), inchToPixel(OUTER_CIRCLE_RADIUS));
		outerRed.setColor(Color.RED);
		outerRed.setFilled(true);
		
		add(outerRed);
		add(midWhite);
		add(innerRed);
		
		
		
		
	}
	private double inchToPixel (double numInches) {
		return numInches*72;
	}
	private double xOfOval (double r) {
		return getWidth()/2 - inchToPixel(r)/2;
	}
	private double yOfOval (double r) {
		return getHeight()/2 - inchToPixel(r)/2;
	}
}
