import java.rmi.*;
import java.rmi.registry.*;
public class BankServer {
	public static void main (String[] args) {
		try {
			BankInterface skeleton= new Bank ();
			Naming.rebind ("rmi://localhost:6000/test",skeleton);
			System.out.println ("Server is waiting for input...");
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}