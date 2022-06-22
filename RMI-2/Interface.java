import java.rmi.*;

public interface Interface extends Remote {
	public int add (int num1, int num2) throws RemoteException;
	public int subtract (int num1, int num2) throws RemoteException;
	public int multiply (int num1, int num2) throws RemoteException;
	public int divide (int num1, int num2) throws RemoteException;
}