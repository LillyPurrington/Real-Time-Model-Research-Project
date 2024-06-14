package model;

import java.util.ArrayList;

/*
 * Finds the least common multiple of various numbers.
 */
public class LeastCommonMultipleFinder {
	
	/*
	 * Finds the least common multiple of an arrayList of Integers.
	 */
	public static int FindLeastCommonMultipleOfArray(ArrayList<Integer> array) {
		int lcm = 1;
		for (int i = 0; i < array.size(); i++) {
			lcm = FindLeastCommonMultipleOfTwoIntegers(array.get(i),lcm);
		}
		return lcm;
	}
	
	/*
	 * Finds the least common multiple of two integers.
	 */
	public static int FindLeastCommonMultipleOfTwoIntegers(int a, int b) {
		return a / GreatestCommonDenominatorFinder.FindGreatestCommonDenominaterOfTwoIntegers(a, b) * b;
	}
}
