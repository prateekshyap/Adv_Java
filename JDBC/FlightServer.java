import java.rmi.*;
import java.rmi.registry.*;

public class FlightServer {
	public static void main (String [] args) {
		try {
			FlightInterface skeleton = new FlightImplementation ();
			Naming.rebind ("rmi://localhost:5000/test",skeleton);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}