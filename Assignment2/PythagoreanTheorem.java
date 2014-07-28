/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;
import java.math.*;

public class PythagoreanTheorem extends ConsoleProgram {
	
	
	public void run() {
		
		int a = readInt("Your first length:");
		int b = readInt("Your second length:");
		print("The hypotenuse length is " + findC(a,b));
		
		
	}
	
	private double findC (int a, int b) {
		
		return Math.sqrt(Math.pow(a, 2)+Math.pow(b, 2));
	}
	}


