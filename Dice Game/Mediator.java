/*
* @author (Tyler Babcock)
* <p> (Mediator)
* <p> (Controls and runs most of the overall pig die game)
*/

/*
	The rules to the dice game Pig

	Number of Players: 2 + 
	Game Duration: < 30 mins
	Players Aged: 6 +
 
	Play begins with the selection of a target game score.
	The users then decide which single die value will be
	the "Pig" value that enhances scoring.

	To Play: Two players take turns to roll both dice, 
	they can roll as many times as they choose in one turn.
	
	A player scores the sum of the two dice thrown and 
	gradually reaches a higher score as the player continues to roll.

	If a single number "Pig" value is thrown on either die, the score 
	for that whole turn is lost. However a double "Pig" value counts as 25.
	The first player to reach or surpass the target score wins unless the other 
	player scores more in the same round. This means that everyone in 
	the game will the same number of turns.
	
	The game may NOT end in a tie. If both players have reached the goal score 
	at the end of a round and the score is tied then another round must be played.

 */

import java.util.Scanner;

public class Mediator
{
	private final char _YES = 'Y';

	// central method to begin and manage game play
	public void start()
	{
		Scanner kb = new Scanner(System.in);
		int score;
		int pigNum;
		int roundNum = 0;

		do
		{
			// Gets max playing value and pig value from user
			score = getInitialMax(kb);
			pigNum = getPigValue(kb);
			// Creates two GamePlayer objects
			PigPlayer one = new PigPlayer(pigNum);
			PigPlayer two = new PigPlayer(pigNum);

			// Players take turns while bellow the limit and not tied
			do
			{
				// Adds round number and players continue to take turns
				roundNum++;
				System.out.println("\nPlayer 1");
				takeTurn(kb, one);
				System.out.println("\nPlayer 2");
				takeTurn(kb, two);
				System.out.println("\nRound: " + roundNum + ": Player 1: " + one.currentTotal() + " -- Player 2:  "
						+ two.currentTotal());
				// Checks for tie over the limit
				if (one.currentTotal() >= score && two.currentTotal() == one.currentTotal())
				{
					System.out.println("The result was a tie. Play a tie breaker round.");
				}

			} while ((one.currentTotal() < score && two.currentTotal() < score)
					|| one.currentTotal() == two.currentTotal());
			// Displays which player wins when they achieve the score goal
			if (one.currentTotal() > two.currentTotal())
			{
				System.out.println("Player 1 wins");
			} else
			{
				System.out.println("Player 2 wins");
			}
			// Prompts the user if they want to play again or not
			System.out.println("Would you like to play again? Respond (Y/N) only.");
			roundNum = 0;

		} while (yesResponse(kb));
		System.out.println("Thanks for playing!");

	}

	//
	// Returns the initial max score
	// (loops until a value between 1 <= score <= 100 is entered)
	//
	private int getInitialMax(Scanner kb)
	{
		int score;
		do
		{
			System.out.println("What score would you like to play to? (100 max) ");
			score = kb.nextInt();
		} while (score > 100 || score < 1);
		return score;
	}

	//
	// Returns the initial max score (loops until a value between 1 <= score <=
	// 100 is entered)
	//
	private int getPigValue(Scanner kb)
	{
		int pigVal;
		do
		{
			System.out.println("What will be the Pig value? (1-6)");
			pigVal = kb.nextInt();
		} while (pigVal < 1 || pigVal > 6);
		return pigVal;
	}

	//
	// method for managing a single session of rolling dice
	//
	private void takeTurn(Scanner kb, PigPlayer player)
	{
		boolean keepRolling = true;

		do
		{
			// Roll the dice
			player.rollDice();

			// Report the result
			System.out.println(player.lastRoll() + " scored " + player.evaluate() + " points.");

			// Did the player pig out?
			if (player.piggedOut())
			{
				System.out.println("You pigged out this turn.");
			} else
			{
				//
				// Roll again; see if the user wants to roll again to add to
				// total or pass and keep current points
				//
				System.out.println("Your current round score is " + player.currentRound() + " points. (total: "
						+ player.currentTotal() + ") Keep rolling? Respond (Y/N) only.");

				if (!yesResponse(kb))
				{
					keepRolling = false;
					int roundScore = player.doneRolling();
					System.out.printf("Your total for the round was %d and your total score is %d.\r\n", roundScore,
							player.currentTotal());
				}
			}

		} while (!player.piggedOut() && keepRolling);
	}

	//
	// Returns true if the user enters a 'y' or 'Y'
	// as the first character of input
	//
	public boolean yesResponse(Scanner kb)
	{
		String response = kb.next();
		return response.toUpperCase().charAt(0) == _YES;
	}
}
