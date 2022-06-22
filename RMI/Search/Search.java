import java.rmi.*;
import java.rmi.server.*;

public class Search extends UnicastRemoteObject implements SearchInterface {
	public Search () throws RemoteException {
		super ();
	}
	public int binarySearch (int[] arr, int key, int l, int r) {
		if (r>= 1) {
			int mid= l+ (r-l)/2;
			if (arr [mid]== key) 
				return mid;
			else if (arr [mid]> key)
				return binarySearch (arr, key, l, mid-1);
			else
				return binarySearch (arr, key, mid+1, r);
		}
		return -1;
	}
	public int linearSearch (int[] arr, int key) {
		int index=-1;
		for (int i=0; i<arr.length; i++)
			if (arr[i]== key) {
				index= i;
				break;
			}
		return index;
	}
	public int[] sort (int[] arr) {
		for (int i=0;i<arr.length-1;i++) {
			for (int j=0;j<arr.length-i-1;j++) {
				if (arr [j] > arr [j+1]) {
					//swap both of them
					int temp=arr [j];
					arr [j]=arr [j+1];
					arr [j+1]=temp;
				}
			}
		}
		return arr;
	}
}