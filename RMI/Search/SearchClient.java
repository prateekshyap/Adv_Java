import java.rmi.*;
import java.io.*;
public class SearchClient {
	public static void main (String[] args) {
		try{
			SearchInterface stub= (SearchInterface)Naming.lookup("rmi://localhost:4000/test");//connected with the server
			BufferedReader br= new BufferedReader (new InputStreamReader (System.in));
			System.out.println ("Enter the dimension of the array- ");
			int dim=Integer.parseInt (br.readLine());
			int[] arr=new int[dim];
			System.out.println ("Enter the numbers-");
			for (int i=0; i<dim; i++)
				arr[i]= Integer.parseInt (br.readLine ());
			System.out.println("Enter the number which you want to search-");
			int key=Integer.parseInt(br.readLine());
			System.out.println("1- Linear Search\n2- Binary Search\nEnter your choice-");
			int choice=Integer.parseInt (br.readLine());
			switch (choice) {
				case 1:
					int index=stub.linearSearch(arr,key);
					if (index== -1)
						System.out.println ("Not found!");
					else
						System.out.println ("Found at index "+index);
					break;
				case 2:
					index=stub.binarySearch(arr,key,0,arr.length-1);
					if (index== -1)
						System.out.println ("Not found!");
					else
						System.out.println ("Found at index "+index);
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