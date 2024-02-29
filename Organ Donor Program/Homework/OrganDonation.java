/* 
* @author (Tyler Babcock) 
* <p> (OrganDonation) 
* <p> (This code takes in names and organs needed and then matches them with donated organs) 
*/
package Homework;

import java.util.InputMismatchException;
import java.util.Scanner;
import DSAndAlgos.*;

public class OrganDonation
{
	// Initialize an ArrayIndexedList to store the organ needs
	private static ArrayIndexedList<String> organNeeds = new ArrayIndexedList<>();

	public static void main(String[] args)
	{

		// Initialize a Scanner object to read user input
		Scanner input = new Scanner(System.in);
		int choice = 0;

		// Display a menu and read the user's choice until they choose to exit
		do
		{
			System.out.println("\n1.) Register organ needed.");
			System.out.println("2.) Register organ donated.");
			System.out.println("3.) Exit");
			System.out.println("----------------------------");

			System.out.print("Select 1, 2 or 3: ");

			try
			{
				// Read the user's choice and clear the input buffer
				choice = input.nextInt();
				input.nextLine();

				switch (choice)
				{
				case 1:
					// Ask the user for the recipient's name and the organ needed, then add it to
					// the organNeeds list
					System.out.print("\nFor who? ");
					String name = input.nextLine();
					System.out.print("\nWhat organ? ");
					String organ = input.nextLine();
					organNeeds.add(name + " needs " + organ);
					break;
				case 2:
					// Ask the user for the donator's name and the organ donated, then search the
					// organNeeds list for a match
					System.out.print("\nBy who? ");
					String donator = input.nextLine();
					System.out.print("\nWhat organ? ");
					String organDonated = input.nextLine();

					int index = -1;
					for (int i = 0; i < organNeeds.size(); i++)
					{
						String need = organNeeds.get(i);
						 // Check if the organ needed matches the organ donated
						if (need.contains(organDonated))
						{
							index = i;
							break;
						}
					}
					 // If no match was found, exit the case
					if (index == -1)
					{
						break;
					} 
					// Remove the organ need from the list
					else
					{
						String need = organNeeds.get(index);
						String recipient = need.substring(0, need.indexOf(" needs"));
						System.out.println("\n" + donator + "'s " + organDonated + " was given to " + recipient);
						organNeeds.removeAt(index);
					}

					break;
				case 3:
					// Display a goodbye message and exit the loop
					System.out.println("\nGoodbye!");
					break;
				default:
					// Display an error message for invalid choices
					System.out.println("\nInvalid selection. Please try again.");
					break;
				}
			}
			// Display an error message for invalid input and clear the input buffer
			catch (InputMismatchException e)
			{
				System.out.println("\nInvalid selection. Please try again.");
				input.nextLine();
			}

		} while (choice != 3);

		input.close();
	}
}
