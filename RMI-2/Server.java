import java.rmi.*;
import java.rmi.registry.*;

public class Server {
	public static void main (String [] args) {
		try {
			Interface skeleton = new Implementation ();
			Naming.rebind ("rmi://localhost:5000/test",skeleton);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}