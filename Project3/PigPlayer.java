/*
* @author (Tyler Babcock)
* <p> (PigPlayer)
* <p> (Project 3)
* <p> (Uses methods that keep track of the total and round scores and keeps track of pig values)
*/
/*
	The rules to the dice game Pig

	Number of Players: 2 + 
	Game Duration: < 30 mins
	Players Aged: 6 +

	You will need: 2 dice and paper to score on.

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

// this class manages the state of the dice and the scoring
public class PigPlayer
{
	// class level variables
	// keep track of total and round scores as well as the two dice.
	private int _totalScore = 0;
	private int _roundScore = 0;
	private Die _die1;
	private Die _die2;
	private int _pigValue = 1;

	// constructor to set the beginning state of the object
	// and accept a value that will be used for special scoring.
	public PigPlayer(int pigValue)
	{
		_pigValue = pigValue;
		_die1 = new Die();
		_die2 = new Die();
	}

	// accessor for total score
	public int currentTotal()
	{
		return _totalScore;
	}

	// accessor for this round score
	public int currentRound()
	{
		return _roundScore;
	}

	// accessor to see if the user has rolled a single "1" and loses turn
	public boolean piggedOut()
	{
		if ((_pigValue == _die1.sideValue() || _die2.sideValue() == _pigValue)
				&& !(_die1.sideValue() == _die2.sideValue()))
		{
			return true;
		}
		return false;
	}

	// mutator that simulates rolling two dice and evaluating the resulting score
	public void rollDice()
	{
		// Roll the die
		_die1.roll();
		_die2.roll();
	}

	// accessor for a formatted string of what the last roll looked like
	public String lastRoll()
	{
		return "D1 (" + _die1.sideValue() + "), D2 (" + _die2.sideValue() + ")";
	}

	// Used to look once at the current roll and calculate scoring
	// for both the current roll and the accumulation of the round score.
	// This is where all score manipulation occurs.
	public int evaluate()
	{
		// Sets round score to 0
		if (singlePigRolled())
		{
			_roundScore = 0;
		}
		// Sets player's round score to 25
		else if (doublePigRolled())
		{
			_roundScore += 25;
		}
		// Adds the dice values rolled to round score
		else
		{
			_roundScore += _die1.sideValue() + _die2.sideValue();
		}
		return _roundScore;
	}

	//
	// mutator to end a round and add this round to the game total.
	// Will also return the total value of the current round
	// and reset the round total for next turn.
	//
	public int doneRolling()
	{
		_totalScore += _roundScore;
		int temp = _roundScore;
		_roundScore = 0;
		return temp;
	}

	// ***************************** Private methods for assisting other class
	// methods *****************************************

	// accessor
	// was just one of the rolled dice the Pig value?
	private boolean singlePigRolled()
	{
		if ((_die1.sideValue() == _pigValue || _die2.sideValue() == _pigValue)
				&& !(_die1.sideValue() == _die2.sideValue()))
		{
			return true;
		}
		return false;
	}

	// accessor
	// were both of the rolled dice the Pig value?
	private boolean doublePigRolled()
	{
		if (_die1.sideValue() == _pigValue && _die2.sideValue() == _pigValue)
		{
			return true;
		}
		return false;
	}

}
