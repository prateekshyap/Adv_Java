import java.rmi.*;
public interface SortInterface extends Remote {
	public int[] bubbleSort (int[] arr) throws RemoteException;
	public int[] quickSort (int[] arr) throws RemoteException;
	public int[] mergeSort (int[] arr) throws RemoteException;
	public int[] heapSort (int[] arr) throws RemoteException;
}