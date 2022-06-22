import java.rmi.*;
public interface SearchInterface extends Remote {
	public int binarySearch (int[] arr, int key, int l, int r) throws RemoteException;
	public int linearSearch (int[] arr, int key) throws RemoteException;
}