/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

import java.awt.*;

public class HangmanCanvas extends GCanvas {
	
	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 56;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	private static final int WIDTH = 377;
	private static final int HEIGHT = 472;
	
/** Resets the display so that only the scaffold appears */
	public void reset() {
	
		removeAll();
		
		add(scaffold);
		add(beam);
		add(rope);

	}
/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		
		if (count > 0) remove(dashedWord);
		dashedWord = new GLabel (word, 100, 425);
		dashedWord.setColor(Color.BLUE);
		dashedWord.setFont(new Font("Serif", Font.BOLD, 18));

		add(dashedWord);
		
		count++;
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
	
		addHangmanPart();
		addWrongLetter(letter);
		
		
		
		
	}
	
	private void addWrongLetter(char letter) {
		
		wrongLetters += letter;
		GLabel wrongList = new GLabel (wrongLetters, 100, 465);
		wrongList.setColor(Color.RED);
		wrongList.setFont(new Font("Serif", Font.BOLD, 18));

		add(wrongList);
	}
	
	private void addHangmanPart () {
		
		
		GObject [] hangmanParts;
		hangmanParts = new GObject [8];
		
		hangmanParts[0] = head;
		hangmanParts[1] = body;
		hangmanParts[2] = leftArm;
		hangmanParts[3] = rightArm;
		hangmanParts[4] = bottomLeftSide;
		hangmanParts[5] = bottomRightSide;
		hangmanParts[6] = leftFoot;
		hangmanParts[7] = rightFoot;
		
		add(hangmanParts[i]);
		
		i++;
		
	}
	
	private GCompound CompoundObject (GLine a, GLine b) {
		GCompound gcomp = new GCompound(); 
		gcomp.add(a);
		gcomp.add(b);
		return gcomp;
	}
	
	
	/* All the Hangman GObjets */	
	private String wrongLetters = "";
	
	private GLabel dashedWord;  
	
	private int count = 0;
	
	private int i = 0;
	
	private GLine scaffold = new GLine (WIDTH/2 - BEAM_LENGTH,
										HEIGHT/2 + SCAFFOLD_HEIGHT/2,
										WIDTH/2 - BEAM_LENGTH,
										HEIGHT/2 - SCAFFOLD_HEIGHT/2);
	
	private GLine beam = new GLine (WIDTH/2 - BEAM_LENGTH, 
									HEIGHT/2 - SCAFFOLD_HEIGHT/2,
									WIDTH/2,
									HEIGHT/2 - SCAFFOLD_HEIGHT/2);
	
	private GLine rope = new GLine (WIDTH/2,
									HEIGHT/2 - SCAFFOLD_HEIGHT/2,
									WIDTH/2,
									HEIGHT/2 - SCAFFOLD_HEIGHT/2+ ROPE_LENGTH);
	
	private GOval head = new GOval (WIDTH/2 - HEAD_RADIUS/2,
									HEIGHT/2  - SCAFFOLD_HEIGHT/2+ ROPE_LENGTH,
									HEAD_RADIUS,
									HEAD_RADIUS);
	
	private GLine body = new GLine (WIDTH/2,
									HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS,
									WIDTH/2,
									HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
	
	private GLine upperLeftArm = new GLine (WIDTH/2,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
											WIDTH/2  - UPPER_ARM_LENGTH,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
	
	private GLine upperRightArm = new GLine (WIDTH/2,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
											WIDTH/2  + UPPER_ARM_LENGTH,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
	
	private GLine lowerLeftArm = new GLine (WIDTH/2  - UPPER_ARM_LENGTH,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
											WIDTH/2  - UPPER_ARM_LENGTH,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
	
	private GLine lowerRightArm = new GLine (WIDTH/2  + UPPER_ARM_LENGTH,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,
											WIDTH/2  + UPPER_ARM_LENGTH,
											HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
	
	private GLine leftHip = new GLine (WIDTH/2,
									   HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
									   WIDTH/2 - HIP_WIDTH,
									   HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);
	
	private GLine rightHip = new GLine (WIDTH/2,
									    HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
									    WIDTH/2 + HIP_WIDTH,
									    HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH);

	private GLine leftLeg = new GLine (WIDTH/2 - HIP_WIDTH,
									   HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
									   WIDTH/2 - HIP_WIDTH,
									   HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);

	private GLine rightLeg = new GLine (WIDTH/2 + HIP_WIDTH,
									    HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH,
									    WIDTH/2 + HIP_WIDTH,
									    HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
	
	private GLine leftFoot = new GLine (WIDTH/2 - HIP_WIDTH,
										HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
										WIDTH/2 - HIP_WIDTH - FOOT_LENGTH,
										HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
	
	private GLine rightFoot = new GLine (WIDTH/2 + HIP_WIDTH,
										 HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,
										 WIDTH/2 + HIP_WIDTH + FOOT_LENGTH,
										 HEIGHT/2 - SCAFFOLD_HEIGHT/2 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
	
	private GCompound leftArm = CompoundObject(upperLeftArm, lowerLeftArm);		
	
	private GCompound rightArm = CompoundObject(upperRightArm, lowerRightArm);
	
	private GCompound bottomLeftSide = CompoundObject(leftHip, leftLeg);
	
	private GCompound bottomRightSide = CompoundObject(rightHip, rightLeg);

	
	
	
	

						

	
	
	
}
