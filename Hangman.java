/*
 This program plays the Hangman game.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	
	public void init(){
		canvas = new HangmanCanvas();
		add(canvas); // initiating the canvas
	}

	public void run() {
		int random = rgen.nextInt(0, lex.getWordCount());
		String word = lex.getWord(random); // obtaining a random word
		int length = word.length();

		String covered = "";
		int chance = 8;
		int leftch = length;

		println("Welcom to Hangman!");
		print("The word now looks like this: ");
		for (int i = 0; i < length; i++) // print "------" indicating the word to be guessed
			covered += '-';
		println(covered);
		canvas.reset();
		canvas.displayWord(covered);

		while (chance > 0) {
			String str1 = readLine("Your guess: ");// readin user input
			char ch1 = str1.charAt(0);
			if (ch1 - 'a' >= 0 && ch1 - 'a' <= 25) {
				ch1 = (char) (ch1 - 'a' + 'A');
			}

			while (ch1 - 'A' < 0 || ch1 - 'A' > 25) {
				str1= readLine("illegal character, please guess again: ");// if input is not a letter, ask for input again
				ch1 = str1.charAt(0);
				if (ch1 - 'a' >= 0 && ch1 - 'a' <= 25) {
					ch1 = (char) (ch1 - 'a' + 'A');
				}
			}

			int pos1 = word.indexOf(ch1); // check if the letter is present in the secret word
			if (pos1 == -1) { // if the letter is absent
				canvas.noteIncorrectGuess(ch1, --chance);
				println("There are no " + ch1 + "'s in the word.");// inform the player it's a wrong guess
				println("You have " + chance + " guesses left.");
			} else {
				for (int i = 0; i < length; i++) {// if the letter is present
					if (covered.charAt(i)!=ch1&&word.charAt(i) == ch1) {
						covered = covered.substring(0, i) + ch1
								+ covered.substring(i + 1);
						leftch--; 
					}
				}
                println("That guess is correct!"); //and inform the player it's a correct guess
				print("The word now looks like this: ");
				println(covered);
				canvas.displayWord(covered); // redisplay the secret word with dashes replaced by guessed letters
				println("You have " + chance + " guesses left.");

			}
			if (leftch == 0) {
				println("you win");
				break;
			}

			if (chance == 0){
				println("You lose");
			    println("The secret word is " + word);
			    }
		}
	}

	private HangmanLexicon lex = new HangmanLexicon();
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanCanvas canvas;
	

}