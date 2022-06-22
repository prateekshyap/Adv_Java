import java.rmi.*;
import java.io.*;

public class Client {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		while (true) {
			System.out.println ("1- Add\n2- Subtract\n3- Multiply\n4- Divide\n5- Quit\nEnter your choice- ");
			int choice = Integer.parseInt (br.readLine ());
			System.out.println ("Enter two integers- ");
			int num1 = Integer.parseInt (br.readLine ());
			int num2 = Integer.parseInt (br.readLine ());
			try {
				Interface stub = (Interface) Naming.lookup ("rmi://localhost:5000/test");
				switch (choice) {
					case 1:
						System.out.println (stub.add (num1, num2));
						break;
					case 2:
						System.out.println (stub.subtract (num1, num2));
						break;
					case 3:
						System.out.println (stub.multiply (num1, num2));
						break;
					case 4:
						System.out.println (stub.divide (num1, num2));
						break;
					case 5:
						System.out.println ("Thanks for using my application!");
						System.exit (0);
					default:
						System.out.println ("Invalid input! Please try again!");
				}
			}
			catch (Exception e) {
				e.printStackTrace ();
			}
		}
	}
}