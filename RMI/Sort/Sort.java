import java.rmi.*;
import java.rmi.server.*;
public class Sort extends UnicastRemoteObject implements SortInterface {
	public Sort () throws RemoteException {
		super ();
	}
	public int[] bubbleSort (int[] arr) {
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
	public int[] quickSort (int[] arr) {
		QuickSort.quick (arr, 0, arr.length-1);
		return arr;
	}
	public int[] mergeSort (int[] arr) {
		MergeSort.mergeS (arr, 0, arr.length-1);
		return arr;
	}
	public int[] heapSort (int[] arr) {
		HeapSort.heap (arr);
		return arr;
	}
}

class QuickSort extends Sort {
	public QuickSort() throws Exception {
		super();
	}
	public static void quick (int[] arr, int low, int high) {
		if (low < high) {
			int mid=partition (arr, low, high);
			quick (arr, low, mid-1);
			quick (arr, mid+1, high);
		}
	}
	public static int partition (int[] arr, int low, int high) {
		int pivot= arr[high];
		int i= low-1;
		for (int j=low;j<high;j++) {
			if (arr[j]<= pivot) {
				i++;
				int temp= arr [i];
				arr [i]= arr [j];
				arr [j]= temp;
			}
		}
		int temp= arr [i+1];
		arr [i+1]= arr [high];
		arr [high]= temp;
		return i+1;
	}
}

class MergeSort extends Sort {
	public MergeSort() throws Exception {
		super();
	}
	public static void mergeS (int[] arr, int left, int right) {
		if (left< right) {
			int mid= left+(right-left)/2;
			mergeS (arr, left, mid);
			mergeS (arr, mid+1, right);
			merge (arr, left, mid, right);
		}
	}
	public static void merge (int[] arr, int left, int mid, int right) {
		//int i, j, k;
		int n1= mid-left+1;
		int n2= right-mid;
		int[] L= new int [n1];
		int[] R= new int [n2];
		for (int i=0; i<n1; i++) 
			L [i]= arr [left+i];
		for (int j=0; j<n2; j++)
			R [j]= arr [mid+1+j];
		int i= 0;
		int j= 0;
		int k= left;
		while (i< n1&& j< n2) {
			if (L [i]<= R [j]) {
				arr [k]= L [i];
				i++;
			}
			else {
				arr [k]= R [j];
				j++;
			}
			k++;
		}
		while (i< n1) {
			arr [k]= L [i];
			i++;
			k++;
		}
		while (j< n2) {
			arr [k]= R [j];
			j++;
			k++;
		}
	}
}

class HeapSort extends Sort {
	public HeapSort() throws Exception {
		super();
	}
	public static void heap (int[] arr) {
		int n= arr.length;
		for (int i= n/2-1; i>= 0; i--)
			heapify (arr, n, i);
		for (int i= n-1; i>= 0; i--) {
			int temp= arr [0];
			arr [0]= arr [i];
			arr [i]= temp;
			heapify (arr, i, 0);
		}
	}
	public static void heapify (int[] arr, int n, int i) {
		int largest= i;
		int left= 2*i+ 1;
		int right= 2*i+ 2;
		if (left< n && arr [left]> arr [largest])
			largest= left;
		if (right< n && arr [right]> arr [largest])
			largest= right;
		if (largest!= i) {
			int temp= arr [i];
			arr [i]= arr [largest];
			arr [largest]= temp;
			heapify (arr, n, largest);
		}
	}
}