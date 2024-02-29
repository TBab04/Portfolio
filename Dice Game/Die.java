
/* 
* @author (Tyler Babcock) 
* <p> (Die) 
* <p> (Runs the dice for the Pig Game) 
*/
import java.util.Random;

// class to manage the value of a single simulated die
public class Die
{
	// class level variables for the methods to manage.
	private int _generator;
	private int _pips = 1;
	private final int _MAX_DOTS = 6;
	private Random _randNum;

	// constructor that will create a Random class
	// and generate a random start value by rolling.
	public Die()
	{
		_randNum = new Random();
	}

	public Die(int seedNum)
	{
		_randNum = new Random(seedNum);
	}

	// mutator to change the value of the die
	// using random number generation
	public void roll()
	{
		_generator = _randNum.nextInt(_MAX_DOTS) + _pips;
	}

	// accessor to return the current value of the die.
	public int sideValue()
	{
		return _generator;
	}

}
