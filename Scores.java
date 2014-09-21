package schoolWork;

import java.awt.event.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

public class Scores {
	int upperScore = 0;
	int acesScore = 0;
	int twosScore = 0;
	int threesScore = 0;
	int foursScore = 0;
	int fivesScore = 0;
	int sixesScore = 0;
	int lowerScore = 0;
	int threeOfAKindScore = 0;
	int fourOfAKindScore = 0;
	int fullHouseScore = 0;
	int smallStraightScore = 0;
	int largeStraightScore = 0;
	int yahtzeeScore = 0;
	int chanceScore = 0;
	int totalScore = 0;
	Yahtzee yahtzee = null;

	public Scores() {

	}

	public Scores(Yahtzee _yahtzee) {
		yahtzee = _yahtzee;
	}

	// Upper Scores
	public void scoreAces() {
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (yahtzee.faceLabels[x] != null
					&& yahtzee.faceLabels[x].getName().equals("0")) {
				upperScore++;
				acesScore++;
			}
		}
	}

	public void scoreTwos() {
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (yahtzee.faceLabels[x] != null
					&& yahtzee.faceLabels[x].getName().equals("1")) {
				upperScore += 2;
				twosScore += 2;
			}
		}
	}

	public void scoreThrees() {
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (yahtzee.faceLabels[x] != null
					&& yahtzee.faceLabels[x].getName().equals("2")) {
				upperScore += 3;
				threesScore += 3;
			}
		}
	}

	public void scoreFours() {
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (yahtzee.faceLabels[x] != null
					&& yahtzee.faceLabels[x].getName().equals("3")) {
				upperScore += 4;
				foursScore += 4;
			}
		}
	}

	public void scoreFives() {
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (yahtzee.faceLabels[x] != null
					&& yahtzee.faceLabels[x].getName().equals("4")) {
				upperScore += 5;
				fivesScore += 5;
			}
		}
	}

	public void scoreSixes() {
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (yahtzee.faceLabels[x] != null
					&& yahtzee.faceLabels[x].getName().equals("5")) {
				upperScore += 6;
				sixesScore += 6;
			}
		}
	}

	// Lower Scores
	public void scoreThreeOfAKind() {
		int score = 0;
		int counter = 0;
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			score += (Integer.parseInt(yahtzee.faceLabels[x].getName()) + 1);
		}
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			counter = 0;

			for (int y = 0; y < yahtzee.faceLabels.length; y++) {
				if (yahtzee.faceLabels[x] != null
						&& yahtzee.faceLabels[x].getName().equals(
								yahtzee.faceLabels[y].getName()) && y != x) {
					counter++;
					if (counter == 2) {
						threeOfAKindScore = score;
						lowerScore += threeOfAKindScore;
						break;
					}
				}
			}
			if (counter == 2) {
				break;
			}
		}

	}

	public void scoreFourOfAKind() {
		int score = 0;
		int counter = 0;		
		
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			score += (Integer.parseInt(yahtzee.faceLabels[x].getName()) + 1);
		}
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			counter = 0;
			for (int y = 0; y < yahtzee.faceLabels.length; y++) {
				if (yahtzee.faceLabels[x] != null
						&& yahtzee.faceLabels[x].getName().equals(
								yahtzee.faceLabels[y].getName()) && y != x) {
					counter++;
					if (counter == 3) {
						fourOfAKindScore = score;
						lowerScore += fourOfAKindScore;
						break;
					}
				}
			}
			if (counter == 3) {
				break;
			}
		}

	}

	public void scoreFullHouse() {
		int score = 25;
		Map threeOfAKindTracker = new HashMap();
		Map pairTracker = new HashMap();
		int threeOfAKindCounter = 0;
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {

			threeOfAKindCounter = 0;
			for (int y = 0; y < yahtzee.faceLabels.length; y++) {
				if (yahtzee.faceLabels[x] != null
						&& yahtzee.faceLabels[x].getName().equals(
								yahtzee.faceLabels[y].getName())) {
					threeOfAKindCounter++;
					if (threeOfAKindCounter == 3) {
						threeOfAKindTracker.put(yahtzee.faceLabels[x].getName(), null);								
						break;
					}
				}

			}
			if (threeOfAKindCounter == 3)
				break;
		}
		if (threeOfAKindCounter == 3) {
			for (int z = 0; z < yahtzee.faceLabels.length; z++) {
				int pairCounter = 0;
				for (int v = 0; v < yahtzee.faceLabels.length; v++) {
					if (yahtzee.faceLabels[z] != null
							&& !threeOfAKindTracker
									.containsKey(yahtzee.faceLabels[z]
											.getName())
							&& !pairTracker.containsKey(yahtzee.faceLabels[z]
									.getName())
							&& yahtzee.faceLabels[z].getName().equals(
									yahtzee.faceLabels[v].getName()) && v != z) {
						pairCounter++;
					}
					if (pairCounter == 1) {
						pairTracker.put(yahtzee.faceLabels[z].getName(), null);
						pairCounter = 0;
						fullHouseScore = score;
						lowerScore += fullHouseScore;
					}
				}
			}

		}
	}

	public void scoreSmallStraight() {
		int score = 30;
		int counter = 0;
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (x < yahtzee.faceLabels.length - 1) {
				int faceLabelIndexOne = Integer
						.parseInt(yahtzee.faceLabels[x + 1].getName());
				int faceLabelIndexTwo = (Integer.parseInt(yahtzee.faceLabels[x]
						.getName()));
				if (faceLabelIndexOne == (faceLabelIndexTwo + 1)
						|| faceLabelIndexOne == (faceLabelIndexTwo - 1)) {
					counter++;
				}
				if (counter == 3) {
					smallStraightScore = score;
					lowerScore += smallStraightScore;
					break;

				}
			}

		}
	}

	public void scoreLargeStraight() {
		int score = 40;
		int counter = 0;
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			if (x < yahtzee.faceLabels.length - 1) {
				int faceLabelIndexOne = Integer
						.parseInt(yahtzee.faceLabels[x + 1].getName());
				int faceLabelIndexTwo = (Integer.parseInt(yahtzee.faceLabels[x]
						.getName()));
				if (faceLabelIndexOne == (faceLabelIndexTwo + 1)
						|| faceLabelIndexOne == (faceLabelIndexTwo - 1)) {
					counter++;
				}
				if (counter == 4) {
					largeStraightScore = score;
					lowerScore += largeStraightScore;
					break;

				}
			}

		}
	}

	public void scoreYahtzee() {
		int counter = 0;
		int score = 50;
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			counter = 0;
			for (int y = 0; y < yahtzee.faceLabels.length; y++) {
				if (yahtzee.faceLabels[x] != null
						&& yahtzee.faceLabels[x].getName().equals(
								yahtzee.faceLabels[y].getName()) && y != x) {
					counter++;
					if (counter == 4) {
						yahtzeeScore = score;
						lowerScore += yahtzeeScore;
					}
				}
			}
			if (counter == 4) {
				break;
			}
		}
	}

	public void scoreChance() {
		int score = 0;
		for (int x = 0; x < yahtzee.faceLabels.length; x++) {
			score += (Integer.parseInt(yahtzee.faceLabels[x].getName()) + 1);
		}
		chanceScore = score;
		lowerScore += chanceScore;
	}

	public int getUpperScore() {
		return upperScore;
	}

	public int getLowerScore() {
		return lowerScore;
	}

	public int getTotalScore() {
		totalScore = getUpperScore() + getLowerScore();
		return totalScore;
	}

}
