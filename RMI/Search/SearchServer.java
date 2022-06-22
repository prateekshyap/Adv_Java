import java.rmi.*;
import java.rmi.registry.*;
public class SearchServer {
	public static void main (String[] args) {
		try{
			SearchInterface skeleton=new Search ();
			Naming.rebind ("rmi://localhost:4000/test",skeleton);
			System.out.println ("Server is waiting for input...");
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}