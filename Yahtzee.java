import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	/*initiation according to the number of players*/

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players"); // ask for the number of players
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
			// for each players, ask for their name
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		// integer arrays to store scores of different categories 
		int[] totalScore = new int[nPlayers + 1];
		int[] upperScore = new int[nPlayers + 1];
		// boolean arrays to store whether a category has been selected from each players
		boolean[][] seletedCategory = new boolean[nPlayers + 1][N_CATEGORIES + 1];
		
		int rank = 0;

		for (int j = 0; j < N_SCORING_CATEGORIES; j++) {
			for (int i = 1; i <= nPlayers; i++) {
				int player = i;
				int[] dice = rollDice(player);

				int category = display.waitForPlayerToSelectCategory();
				while (seletedCategory[i][category]) { 
					// cannot select a selected category
					category = display.waitForPlayerToSelectCategory();
				}
				seletedCategory[i][category] = true;
				// set to a previously unseleted category to selected
				int score = getScore(category, dice);
				/* calculate score of dice according scoring system of a selected category */
				
				totalScore[i] += score;
				
				// update total score after this round of seletion
				
				display.updateScorecard(category, player, score);
				display.updateScorecard(TOTAL, player, totalScore[i]);
				
				// update upper score after this round of seletion if necessary

				if (category <= SIXES) {
					upperScore[i] += score;
					display.updateScorecard(UPPER_SCORE, player, upperScore[i]);
				}
				
				// checking conditions for upper bonus
				if (upperScore[i] > 63) {
					display.updateScorecard(UPPER_BONUS, player, 35);
					totalScore[i] += 35;
					display.updateScorecard(TOTAL, player, totalScore[i]);
				}
			}
		}
		/* optional codes for fame hall 
		
		 * for (int i = 1; i <= nPlayers; i++) { for(int j=9;j>=0;j--){ if
		 * (fh[j].score<totalScore[i]){ fh[j].score=totalScore[i]; rank=j+1;} }
		 * display.printMessage("congraduations, "+" you are No."+ rank); }
		 */
	}
	
	/*
	 * the method rollDice is responsible for generating 5 random number
	 * between 1-6 each time the player role the dices
	 */

	private int[] rollDice(int player) {

		display.waitForPlayerToClickRoll(player); // wait for user response
		int[] dice = new int[5];
		for (int i = 0; i < 5; i++) {
			dice[i] = rgen.nextInt(1, 6); // generate random number mimicing dice rolling
		}
		display.displayDice(dice);
		display.printMessage("1st time");

		display.waitForPlayerToSelectDice();
		for (int i = 0; i < 5; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}

		display.displayDice(dice);
		display.printMessage("2nd time");

		display.waitForPlayerToSelectDice();
		for (int i = 0; i < 5; i++) {
			if (display.isDieSelected(i)) {
				dice[i] = rgen.nextInt(1, 6);
			}
		}

		display.displayDice(dice);
		display.printMessage("3rd time. please select a category");
		return dice;

	}
	/* the getScore() method translate the scoring system of each cagegory 
	 * accofing to the game rule
	 */

	private int getScore(int category, int[] dice) {
		int score = 0;
		int[] drawer = new int[7];
		switch (category) {
			/* for ONE to SIXES, just add up the dices matching the category*/
		case ONES:
			for (int i = 0; i < N_DICE; i++)
				if (dice[i] == 1)
					score += 1;
			return score;
		case TWOS:
			for (int i = 0; i < N_DICE; i++)
				if (dice[i] == 2)
					score += 2;
			return score;
		case THREES:
			for (int i = 0; i < N_DICE; i++)
				if (dice[i] == 3)
					score += 3;
			return score;
		case FOURS:
			for (int i = 0; i < N_DICE; i++)
				if (dice[i] == 4)
					score += 4;
			return score;
		case FIVES:
			for (int i = 0; i < N_DICE; i++)
				if (dice[i] == 5)
					score += 5;
			return score;
		case SIXES:
			for (int i = 0; i < N_DICE; i++)
				if (dice[i] == 6)
					score += 6;
			return score;
			/*
			put all dices, based on their numbers, in the drawers labels 1-6 
			if one of the draw has at least three dices, then add their number up
			otherwise, player doesn't score in this category
			*/
		case THREE_OF_A_KIND:
			for (int i = 0; i < N_DICE; i++) {
				drawer[dice[i]]++;
				score += dice[i];
			}
			for (int i = 1; i <= 6; i++) {
				if (drawer[i] >= 3)
					return score;
			}
			return 0;
			/*
			put all dices, based on their numbers, in the drawers labels 1-6 
			if one of the draw has at least four dices, then add their number up
			otherwise, player doesn't score in this category
			*/
		case FOUR_OF_A_KIND:
			for (int i = 0; i < N_DICE; i++) {
				drawer[dice[i]]++;
				score += dice[i];
			}
			for (int i = 1; i <= 6; i++) {
				if (drawer[i] >= 4)
					return score;
			}
			return 0;
			/*
			put all dices, based on their numbers, in the drawers labels 1-6 
			if more than four drawers are empty, the player score for fullhouse
			except one condition need to be ruled out:
			four dices of one kind and one dice of another
			*/
		case FULL_HOUSE:
			for (int i = 0; i < N_DICE; i++) {
				drawer[dice[i]]++;
				score += dice[i];
			}
			int emptyDrawer = 0;
			for (int i = 1; i <= 6; i++) {
				if (drawer[i] == 0)
					emptyDrawer++;
			}
			for (int i = 1; i <= 6; i++) {
				if (drawer[i] == 4)
					emptyDrawer = -1;
			}
			if (emptyDrawer >= 4)
				return 25;
			return 0;
			/*
			test all the possibility for a linked 4 numbers from the dices
			*/
		case SMALL_STRAIGHT:
			for (int i = 0; i < N_DICE; i++) {
				drawer[dice[i]]++;
			}
			if (!unlinked4(1, drawer) || !unlinked4(2, drawer)
					|| !unlinked4(3, drawer))
				return 30;
			return 0;
		case LARGE_STRAIGHT:
			for (int i = 0; i < N_DICE; i++) {
				drawer[dice[i]]++;
			}
			if (!unlinked5(1, drawer) || !unlinked5(2, drawer))
				return 40;
			return 0;
		case YAHTZEE:
			for (int i = 1; i < N_DICE; i++)
				if (dice[0] != dice[i])
					return 0;
			return 50;
		case CHANCE:
			for (int i = 0; i < N_DICE; i++)
				score += dice[i];
			return score;
		default:
			return 0;
		}
	}
	/*
	check whether 4 continuous drawers all have elements
	*/
	private boolean unlinked4(int start, int[] drawer) {
		return drawer[start++] == 0 || drawer[start++] == 0
				|| drawer[start++] == 0 || drawer[start++] == 0;
	}

	private boolean unlinked5(int start, int[] drawer) {
		return drawer[start++] == 0 || drawer[start++] == 0
				|| drawer[start++] == 0 || drawer[start++] == 0
				|| drawer[start++] == 0;
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}