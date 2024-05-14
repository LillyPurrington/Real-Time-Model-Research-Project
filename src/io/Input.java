package io;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Handles input
 * @author Lilly Purrington
 */
public class Input {

	//The scanner used in all the input
	private Scanner scan = new Scanner(System.in);
	
	//Restricts possible inputs to a preseleceted list.
	public String restrictedInput(String prompt,String[] validAnswers) {
				
		boolean valid = false;
		
		String choice = null;
		
		//Converted to a list because it was the easiest way to check if it contains something.
		List<String> list = Arrays.asList(validAnswers);
		
		while (!valid) {
			Output.textDisplay(prompt);
			choice = scan.next();
			valid = list.contains(choice);
		}
				
		return choice;
	}
	
	//Gets a numerical input, theoretically I could make a numericalRestictedInput method, by combining the two, but it would never be used in the program
	public int numericalInput(String prompt) {
		boolean valid = false;
		String choice = null;
		int intChoice = 0;
		
		while (!valid) {
			Output.textDisplay(prompt);
			choice = scan.next();
			
			try {
				intChoice = Integer.parseInt(choice);
				valid = true;
			}catch(NumberFormatException e) {
				Output.textDisplay("An integer is needed.");
			}
		}
				
		return intChoice;
	}
	
	//Closing the scanner, must be run at end.
	public void close() {
		scan.close();
	}
}
