import java.io.*;
import java.rmi.*;
public class Customer {
	public static void main (String[] args) throws IOException {
		BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
		BankInterface stub= null;
		try {
			stub= (BankInterface) Naming.lookup ("rmi://localhost:6000/test");
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		System.out.println ("Your main balance is "+stub.getMainBalance ());
		System.out.println ("Welcome to my bank!");
		while (true) {
			System.out.println ("1- Deposit\n2- Withdraw\n3- Fast withdraw\n4- Mini Statement\n5- Change Pin\n6- Quit the application\nEnter your choice-");
			int choice= Integer.parseInt (br.readLine ());
			int pin=0;
			double newBalance=0, amount=0;
			switch (choice) {
				case 1:
					System.out.println ("Enter your pin- ");
					pin= Integer.parseInt (br.readLine ());
					System.out.println ("Enter the amount you want to deposit-");
					amount= Double.parseDouble (br.readLine());
					newBalance= stub.deposit (amount, pin);
					if (newBalance==-1)
						System.out.println ("Incorrect pin! Please try again!");
					else
						System.out.println ("Transaction successful!\nYour main balance is "+newBalance);
					break;
				case 2:
					System.out.println ("Enter your pin-");
					pin= Integer.parseInt (br.readLine ());
					System.out.println ("Enter the amount you want to withdraw-");
					amount= Double.parseDouble (br.readLine ());
					newBalance= stub.withdraw (amount, pin);
					if (newBalance==-2)
						System.out.println ("Insufficient main balance!");
					else if (newBalance==-1)
						System.out.println ("Incorrect pin!");
					else
						System.out.println ("Transaction successful!\nYour main balance is "+newBalance);
					break;
				case 3:
					System.out.println ("Enter your pin-");
					pin= Integer.parseInt (br.readLine ());
					System.out.println ("1- 100\n2- 200\n3- 500\n4- 1000\n5- 2000\n6- 5000\n7- 10000\n8- 15000\n9- 20000\n10- 50000\nEnter your choice-");
					int ch=Integer.parseInt (br.readLine ());
					switch (ch) {
						case 1: amount= 100; break;
						case 2: amount= 200; break;
						case 3: amount= 500; break;
						case 4: amount= 1000; break;
						case 5: amount= 2000; break;
						case 6: amount= 5000; break;
						case 7: amount= 10000; break;
						case 8: amount= 15000; break;
						case 9: amount= 20000; break;
						case 10: amount= 50000; break;
						default: amount= -1;
					}
					if (amount== -1) {
						System.out.println ("Invalid amount! Please try again!");
						System.exit(0);
					}
					else {
						newBalance= stub.fastWithdraw (amount, pin);
						if (newBalance==-2)
							System.out.println ("Insufficient main balance!");
						else if (newBalance==-1)
							System.out.println ("Incorrect pin!");
						else
							System.out.println ("Transaction successful!\nYour main balance is "+newBalance);
						break;
					}
				case 4:
					System.out.println ("Last 10 transactions- ");
					//System.out.println ("Amount and Type");
					double[] arr=stub.miniStatement ();
					for (int k=4;k>=0;k--) {
						if (arr [k] < 0) {
							double amt= arr [k] - 2*arr [k];
							System.out.println (amt+" Debited");
						}
						else if (arr [k] > 0) {
							System.out.println (arr [k]+" Credited");
						}
						else {}
					}
					break;
				case 5:
					System.out.println ("Enter your current pin-");
					int currPin= Integer.parseInt (br.readLine ());
					System.out.println ("Enter the first two digits of your new pin wihtout pressing space or enter-");
					int newHalfPin= Integer.parseInt (br.readLine ());
					if (currPin>=0 && currPin<=9999 && newHalfPin>=0 && newHalfPin<=99) {
						int newPin= stub.changePin (newHalfPin, currPin);
						if (newPin==-1)
							System.out.println ("Incorrect current pin! Please try again!");
						else
							System.out.println ("Successfully changed! Your new pin is "+newPin);
					}
					else
						System.out.println ("Invalid pin! Please try again!");
					break;
				case 6:
					System.out.println ("See you again!");
					System.exit (0);
				default:
					System.out.println ("Invalid input!Please try again!");
			}
		}
	}
}