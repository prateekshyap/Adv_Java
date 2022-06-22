import java.rmi.*;
import java.rmi.registry.*;
public class SortServer {
	public static void main (String[] args) {
		try{
			SortInterface skeleton=new Sort ();
			Naming.rebind ("rmi://localhost:5000/test",skeleton);
			System.out.println ("Server is waiting for input...");
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}