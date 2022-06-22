/*
/* pizza = two dimensional matrix representing the pizza
/* r = number of rows
/* c = number of columns
/* l = minimum number of each ingredient cells in a slice
/* h = maximum total number of cells in a slice
/* slices = data structure to store the slices dynamically
/* sliceCounter = to count the number of slices and store the total number of slices
/* factors = a matrix to contain in how many ways we can cut the slices
/* noOfCombinations = total number of ways present in factors matrix
*/
import java.io.*;
import java.util.*;

public class Pizza {
	static int r, c, l, h, sliceCounter;
	static char[][] pizza;
	static ArrayList slices;
	static int[][] factors;
	static int noOfCombinations;
	static int copyIndex = 0;
	static int factorIndex;
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		String[] nums = br.readLine().split (" ");
		r = Integer.parseInt (nums [0]);
		c = Integer.parseInt (nums [1]);
		l = Integer.parseInt (nums [2]);
		h = Integer.parseInt (nums [3]);
		if (r < 1 || r > 1000 || c < 1 || c > 1000 || l < 1 || l > 1000 || h < 1 || h > 1000) {
			System.out.println ("Too big number! Please enter from 1 to 1000!");
			System.exit (0);
		}
		pizza = new char[r][c];
		//taking input for the ingredients
		for (int i = 0 ; i < r ; i++) {
			char[] pizzaIngredients = br.readLine().toCharArray ();
			for (int j = 0 ; j < c ; j++)
				pizza[i][j] = pizzaIngredients[j];
		}
		slices = new ArrayList ();
		//finding the factors of h
		findFactors (h);
		/*for (int i = 0 ; i < noOfCombinations ; i++) {
			for (int j = 0 ; j < 2 ; j++)
				System.out.print (factors [i][j]+" ");
			System.out.println ();
		}*/
		//calling the cutThePizza method
		boolean isPossible = false;
		factorIndex = 0;
		while (true) {
			if (factors [factorIndex][0] <= r && factors [factorIndex][1] <= c)
				break;
			else
				factorIndex++;
		}
		while (isPossible == false) {
			copyIndex = 0;
			int[] temp = new int [2]; 
			for (int i = 0 ; i < 2 ; i++)
				temp [i] = factors [factorIndex][i];
			char[][] pizzaCopy = pizza;
			int[] startingPoints = {0,0};
			isPossible = cutThePizza (r,c,l,h,temp,pizzaCopy,0,0);
			isPossible = true;
			if (isPossible == false) {
				factorIndex++;
				while (true) {
					if (factors [factorIndex][0] <= r && factors [factorIndex][1] <= c)
						break;
					else
						factorIndex++;
				}
			}
			else
				System.out.println ("OK");
		}
	}

	public static boolean cutThePizza (int r, int c, int l, int h, int[] sliceSize, char[][] pizzaCopy, int rowStart, int colStart) {
		//add the slices and check for the next slice
		int mCounter = 0;
		int tCounter = 0;
		int flag = 0;
		for (int i = rowStart ; i < sliceSize [0] ; i++) {
			for (int j = colStart ; j < sliceSize [1] ; j++) {
				if (pizzaCopy [i][j] == 'T') {
					tCounter++;
					/*pizzaCopy [i][j] = 'X';*/
				}
				else if (pizzaCopy [i][j] == 'M') {
					mCounter++;
					/*pizzaCopy [i][j] = 'X';*/
				}
				if (tCounter >= l && mCounter >= l) {
					flag++ ;
					break;
				}
			}
			if (flag != 0)
				break;
		}
		for (int i = rowStart ; i < sliceSize [0] ; i++)
			for (int j = colStart ; j < sliceSize [1] ; j++)
				pizzaCopy [i][j] = 'X';
		slices.add (rowStart);
		slices.add (colStart);
		slices.add (sliceSize [0]-1);
		slices.add (sliceSize [1]-1);
		for (int i = 0 ; i < r ; i++) {
			for (int j = 0 ; j < c ; j++)
				System.out.print (pizzaCopy [i][j]+" ");
			System.out.println ();
		}
		/*if (((rowStart+sliceSize[1]+sliceSize[1]) <= c) && ((colStart+sliceSize[0]+sliceSize[0]) <= r))*/
		if ((colStart+sliceSize[0]+sliceSize[0]) <= r)
			return cutThePizza (r,c,l,h,sliceSize,pizzaCopy,rowStart+sliceSize[0],colStart);
		else if ((rowStart+sliceSize[1]+sliceSize[1]) <= c)
			return cutThePizza (r,c,l,h,sliceSize,pizzaCopy,rowStart,colStart+sliceSize[1]);
		else {
			while (true) {
				if (factors [copyIndex][0] <= r && factors [copyIndex][1] <= c && copyIndex != factorIndex)
					break;
				else
					copyIndex++;
			}
			int[] temp = new int [2];
			for (int k = 0 ; k < 2 ; k++)
				temp [k] = factors [copyIndex][k];
			if ((colStart+temp[0]+temp[0]) <= r)
				return cutThePizza (r,c,l,h,temp,pizzaCopy,rowStart+sliceSize[0],colStart);
			else if ((rowStart+temp[1]+temp[1]) <= c)
				return cutThePizza (r,c,l,h,temp,pizzaCopy,rowStart,colStart+sliceSize[1]);
		}
		return false;
	}

	public static void findFactors (int num) {
		ArrayList al = new ArrayList ();
		int counter = 1;
		while (counter <= num) {
			if (num % counter == 0)
				al.add (counter);
			counter++;
		}
		factors = new int[al.size()][2];
		for (int i = 0, j = al.size()-1 ; i < al.size() && j >= 0 ; i++, j--) {
			factors [i][0] = (int) al.get (i);
			factors [j][1] = (int) al.get (i);
		}
		noOfCombinations = al.size ();
	}
}