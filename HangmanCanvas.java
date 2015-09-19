/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		removeAll();
		GLine line1 = new GLine(getWidth()/2,getHeight()*0.1, getWidth()/2, getHeight()*0.1 + ROPE_LENGTH);
		add(line1);
		GLine line2 = new GLine(getWidth()/2-BEAM_LENGTH,getHeight()*0.1, getWidth()/2,getHeight()*0.1);
		add(line2);
		GLine line3 = new GLine(getWidth()/2-BEAM_LENGTH,getHeight()*0.1,getWidth()/2-BEAM_LENGTH,getHeight()*0.1+SCAFFOLD_HEIGHT);
		add(line3);
		}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		GRect rect = new GRect(0,getHeight()*0.87, getWidth(), getHeight()*0.05);
		rect.setFilled(true);
		rect.setFillColor(Color.lightGray);
		add (rect);
		GLabel wordlabel = new GLabel(word, getWidth()/2, getHeight()*0.9);
		add (wordlabel);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter, int chance) {
		String wrongLetters ="";
		wrongLetters += letter;
		GLabel wordlabe2 = new GLabel(wrongLetters, getWidth()/2+50-chance*10, getHeight()*0.95);
		add (wordlabe2);
		
		/*display the corresponding graph according to the number of chances left for the play to guess */
		switch (chance){
			case 7:
				GOval oval= new GOval(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.1 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				add(oval);
				break;
			case 6:
				GOval oval6= new GOval(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.1 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				add(oval6);
				GLine line = new GLine(getWidth()/2,getHeight()*0.1+1.5*HEAD_RADIUS, getWidth()/2,getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH);
				add(line);
				break;
			case 5:
				GOval oval5= new GOval(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.1 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				add(oval5);
				GLine line5 = new GLine(getWidth()/2,getHeight()*0.1+1.5*HEAD_RADIUS, getWidth()/2,getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH);
				add(line5);
				GLine line51= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3, getHeight()*0.35);
				add(line51);
				break;	
			case 4:
				GOval oval4= new GOval(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.1 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				add(oval4);
				GLine line4 = new GLine(getWidth()/2,getHeight()*0.1+1.5*HEAD_RADIUS, getWidth()/2,getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH);
				add(line4);
				GLine line41= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3, getHeight()*0.35);
				add(line41);
				GLine line42= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3*2, getHeight()*0.35);
				add(line42);
				break;	
			case 3:
				GOval oval3= new GOval(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.1 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				add(oval3);
				GLine line3 = new GLine(getWidth()/2,getHeight()*0.1+1.5*HEAD_RADIUS, getWidth()/2,getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH);
				add(line3);
				GLine line31= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3, getHeight()*0.35);
				add(line31);
				GLine line32= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3*2, getHeight()*0.35);
				add(line32);
				GLine line33= new GLine(getWidth()/2, getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH, getWidth()/3, getHeight()*0.65);
				add(line33);
				break;	
			case 2:
				GOval oval2= new GOval(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.1 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				add(oval2);
				GLine line2 = new GLine(getWidth()/2,getHeight()*0.1+1.5*HEAD_RADIUS, getWidth()/2,getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH);
				add(line2);
				GLine line21= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3, getHeight()*0.35);
				add(line21);
				GLine line22= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3*2, getHeight()*0.35);
				add(line22);
				GLine line23= new GLine(getWidth()/2, getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH, getWidth()/3, getHeight()*0.65);
				add(line23);
				GLine line24= new GLine(getWidth()/2, getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH, getWidth()/3*2, getHeight()*0.65);
				add(line24);
				break;
			case 1:
				GOval oval1= new GOval(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.1 + ROPE_LENGTH, HEAD_RADIUS, HEAD_RADIUS);
				add(oval1);
				GLine line1 = new GLine(getWidth()/2,getHeight()*0.1+1.5*HEAD_RADIUS, getWidth()/2,getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH);
				add(line1);
				GLine line11= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3, getHeight()*0.35);
				add(line11);
				GLine line12= new GLine(getWidth()/2, getHeight()*0.25, getWidth()/3*2, getHeight()*0.35);
				add(line12);
				GLine line13= new GLine(getWidth()/2, getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH, getWidth()/3, getHeight()*0.65);
				add(line13);
				GLine line14= new GLine(getWidth()/2, getHeight()*0.1+HEAD_RADIUS+ BODY_LENGTH, getWidth()/3*2, getHeight()*0.65);
				add(line14);
				GLine line15= new GLine(getWidth()/2-HEAD_RADIUS/2, getHeight()*0.2 + ROPE_LENGTH, getWidth()/2-HEAD_RADIUS/2+HEAD_RADIUS, getHeight()*0.2 + ROPE_LENGTH);
				line15.setColor(Color.RED);
				add(line15);
				break;
			case 0:
				GLabel label0 = new GLabel("You lose", getWidth()/2-15, getHeight()/2);
				label0.setFont("Raavi-20");
				add(label0);
			
		}
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

}