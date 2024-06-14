package model;


/*
 * Finds the greatest common denominator.
 */
public class GreatestCommonDenominatorFinder {
	
	/*
	 * a and b must be greater than 0.
	 * This is a  method for finding GCD called the Euclidean Algorithm.
	 */
	public static int FindGreatestCommonDenominaterOfTwoIntegers(int a, int b) {
		int x = a;
		int y = b;
		while (x != 0) {
			int remainder = x%y;
			y = x;
			x = remainder;
		}
		return y;
	}

}
