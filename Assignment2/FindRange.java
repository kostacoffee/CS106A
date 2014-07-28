/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	private static final int SENTINEL = 0;
	
	public void run() {
		
		println("This program will accept a set of values and then choose the largest and the smalles of those.");
		
		int i = 1;
		int total = 0;
		
		while (true) {
			int num = readInt("number " + i + "= ");
			if (num == SENTINEL) {
				if (i == 1) {
					println("you did not input any numbers");
					break;
					}
				else if (i==2) {
					largest = total;
					smallest = total;
					break;
					}
				break;
				}
			smallestOrLargest(num);
			total += num;
			i++;
		}
		if (i==1) {
			return;
			}
		else {
			println("smallest = " + smallest);
			println("largest = " + largest);
			}
	}
		
		
	private void smallestOrLargest (int num) {
		if (num > largest){
			largest = num;
			}
		else if (num < smallest) {
			smallest = num;
			}
	}
	private int smallest = (int)Double.POSITIVE_INFINITY;
	private int largest = (int)Double.NEGATIVE_INFINITY;
}

