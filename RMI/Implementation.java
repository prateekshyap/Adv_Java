import java.rmi.*;
import java.rmi.server.*;

public class Implementation extends UnicastRemoteObject implements Interface {
		public Implementation () throws RemoteException {
			super ();
		}

		@Override
		public String getString () {
			return "Successful connection!";
		}
}