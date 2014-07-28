/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	
	private static final int SENTINEL = 1;
	
	public void run() {
		
		int n = readInt("What integer do you want? ");
		
		hailstone(n);
		
	}
	private void hailstone(int n) {
		
		while (true) {
			if (n == SENTINEL) {
				break;
			}
			else if (n % 2 == 0) {
				println(n + " is even, so I take half: " + n/2);
				n = n/2;
			}
			else {
				println(n + " is odd, so I 3n + 1: " + (3*n + 1));
				n = 3*n + 1;
			}
		}
	}
}

