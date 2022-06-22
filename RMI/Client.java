import java.rmi.*;
import java.io.*;

public class Client {
	public static void main (String [] args) {
		Interface stub = null;
		try {
			stub = (Interface) Naming.lookup ("rmi://localhost:2000/test");
			System.out.println (stub.getString ());
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}