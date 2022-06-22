import java.rmi.*;
import java.io.*;
public class SortClient {
	public static void main (String[] args) {
		try{
			SortInterface stub= (SortInterface)Naming.lookup("rmi://localhost:5000/test");//connected with the server
			BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
			/*System.out.println ("Enter the dimension of the array- ");
			int dim=Integer.parseInt (br.readLine());*/
			int[] arr=new int[10];
			System.out.println ("Enter 10 numbers-");
			for (int i=0; i<10; i++)
				arr[i]= Integer.parseInt (br.readLine ());
			System.out.println("1- Bubble Sort\n2- Quick Sort\n3- Merge Sort\n4- Heap Sort\nEnter your choice-");
			int choice=Integer.parseInt (br.readLine());
			switch (choice) {
				case 1:
					System.out.println ("After Bubble Sort- ");
					for (int i=0; i<10; i++)
						System.out.print (stub.bubbleSort(arr)[i]+"\t");
					break;
				case 2:
					System.out.println ("After Quick Sort- ");
					for (int i=0; i<10; i++)
						System.out.print (stub.quickSort(arr)[i]+"\t");
					break;
				case 3:
					System.out.println ("After Merge Sort- ");
	  				for (int i=0; i<10; i++)
						System.out.print (stub.mergeSort(arr)[i]+"\t");
					break;
				case 4:
					System.out.println ("After Heap Sort- ");
					for (int i=0; i<10; i++)
						System.out.print (stub.heapSort(arr)[i]+"\t");
					break;
				default:
					System.out.println("Please enter a valid choice!");
					System.exit(0);
			}
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}