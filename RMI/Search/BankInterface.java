import java.rmi.*;
public interface BankInterface extends Remote {
	public double getMainBalance () throws RemoteException;
	public double withdraw (double amount, int pin) throws RemoteException;
	public double deposit (double amount, int pin) throws RemoteException;
	public double fastWithdraw (double amount, int pin) throws RemoteException;
	public double[] miniStatement () throws RemoteException;
	public int changePin (int halfPin, int oldPin) throws RemoteException;
}