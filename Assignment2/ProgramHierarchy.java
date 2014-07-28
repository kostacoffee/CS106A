/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	private static final int HEIGHT_OF_BOX = 50;
	
	private static final int WIDTH_OF_BOX = 150;
	
	private static final int SPACE_BETWEEN_BOXES = 25;
	
	public void run() {
		
		int xRef = getWidth()/2-WIDTH_OF_BOX/2- 25 - WIDTH_OF_BOX; // point for which the Bottom left rectangle is in correct place (x-axis)
		int yRef = getHeight()/2-HEIGHT_OF_BOX/2; // point for which the Bottom set is in correct place (y-axis)
		

		GRect top = new GRect(xRef+ SPACE_BETWEEN_BOXES + WIDTH_OF_BOX, yRef-HEIGHT_OF_BOX*2, WIDTH_OF_BOX, HEIGHT_OF_BOX); // Top rectangle
		for (int i = 0; i<3; i++){
			add(new GRect (xRef + SPACE_BETWEEN_BOXES*i + WIDTH_OF_BOX*i, yRef, WIDTH_OF_BOX, HEIGHT_OF_BOX));
			add(new GLine (xRef+(WIDTH_OF_BOX/2)+SPACE_BETWEEN_BOXES*i + WIDTH_OF_BOX*i, yRef, xRef + SPACE_BETWEEN_BOXES + WIDTH_OF_BOX + (WIDTH_OF_BOX/2),  yRef-HEIGHT_OF_BOX));
		}
		add(top);
		
		GLabel graphics = new GLabel ("GraphicsProgram");
		GLabel console = new GLabel ("ConsoleProgram");
		GLabel dialog = new GLabel ("DialogProgram");
		GLabel program = new GLabel ("Program");
		
		add(graphics,xRef + WIDTH_OF_BOX/2 - graphics.getWidth()/2								,yRef + graphics.getAscent()/2 + HEIGHT_OF_BOX/2); // GraphicsProgram label
		add(console,2*(xRef + WIDTH_OF_BOX/2 - console.getWidth()/2 + SPACE_BETWEEN_BOXES/2) ,yRef + console.getAscent()/2 + HEIGHT_OF_BOX/2); //ConsoleProgram label
		add(dialog,3*(xRef + WIDTH_OF_BOX/2 - dialog.getWidth()/2 + SPACE_BETWEEN_BOXES/2) ,yRef + dialog.getAscent()/2 + HEIGHT_OF_BOX/2); // DialogProgram label
		add(program,xRef + 1.5*WIDTH_OF_BOX + SPACE_BETWEEN_BOXES - program.getWidth()/2 ,yRef - 1.5*HEIGHT_OF_BOX + program.getAscent()/2); // Program Label
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
		/*
		add(new GRect (xBotMid, yBotMid, WIDTH_OF_BOX, HEIGHT_OF_BOX));
		TOP (xBotMid, yBotMid-HEIGHT_OF_BOX*2, WIDTH_OF_BOX, HEIGHT_OF_BOX));
		add(new GRect (xBotMid + 50 + WIDTH_OF_BOX*2, yBotMid, WIDTH_OF_BOX, HEIGHT_OF_BOX));
		add(new GRect (xBotMid + 25 + WIDTH_OF_BOX, yBotMid, WIDTH_OF_BOX, HEIGHT_OF_BOX));
		*/

