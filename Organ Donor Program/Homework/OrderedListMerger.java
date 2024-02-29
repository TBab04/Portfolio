/* 
* @author (Tyler Babcock) 
* <p> (OrderedListMerger) 
* <p> (Takes names and puts them in alphabetical order) 
*/
package Homework;
import java.util.Random;
import java.util.Scanner;
import DSAndAlgos.*;

public class OrderedListMerger
{

	public static void main(String[] args)
	{
		// Create two empty ordered lists
		IOrderedList<String> list1 = new LinkedOrderedList<String>();
		IOrderedList<String> list2 = new LinkedOrderedList<String>();

		// Create a scanner to read user input
		Scanner scanner = new Scanner(System.in);

		// Create a random generator to select the list
		Random random = new Random();

		// Prompt the user to enter a name and add it to a random list
		System.out.print("Enter a name (leave blank to proceed to merge phase): ");
		String name = scanner.nextLine();
		while (!name.isEmpty())
		{
			if (random.nextBoolean())
			{
				list1.add(name);
			} else
			{
				list2.add(name);
			}
			System.out.print("Enter a name (leave blank to proceed to merge phase): ");
			name = scanner.nextLine();
		}

		// Merge the two lists into a single output
		while (!list1.isEmpty() && !list2.isEmpty())
		{
			if (list1.peek().compareTo(list2.peek()) <= 0)
			{
				System.out.print(list1.peek() + " ");
				list1.remove(list1.peek());
			} else
			{
				System.out.print(list2.peek() + " ");
				list2.remove(list2.peek());
			}
		}

		// Empty the remaining elements of list1, if any
		while (!list1.isEmpty())
		{

			System.out.print(list1.peek() + " ");
			list1.remove(list1.peek());
		}

		// Empty the remaining elements of list2, if any
		while (!list2.isEmpty())
		{
				System.out.print(list2.peek() + " ");
				list2.remove(list2.peek());
		}
		// Print the end message
		System.out.println("\nGoodbye!");
	}
}
