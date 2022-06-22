import java.util.*;
public class Rough {
	public static void main (String[] args) {
		ArrayList al = new ArrayList ();
		int num = 2025;
		int counter = 1;
		while (counter <= num) {
			if (num % counter == 0)
				al.add (counter);
			counter++;
		}
		int[][] sliceSizes = new int[al.size()][2];
		for (int i = 0, j = al.size()-1 ; i < al.size() && j >= 0 ; i++, j--) {
			sliceSizes [i][0] = (int) al.get (i);
			sliceSizes [j][1] = (int) al.get (i);
		}
		for (int i = 0 ; i < al.size() ; i++) {
			for (int j = 0 ; j < 2 ; j++)
				System.out.print (sliceSizes [i][j]+" ");
			System.out.println ();
		}
	}
}