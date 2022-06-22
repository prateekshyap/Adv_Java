import java.rmi.*;

public interface Interface extends Remote {
	public String getString () throws RemoteException;
}