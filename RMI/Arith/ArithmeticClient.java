import java.rmi.*;
import java.io.*;

public class ArithmeticClient {
	public static void main (String[] args) {
		try {
			Arithmetic stub=(Arithmetic)Naming.lookup("rmi://localhost:2000/test");
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter two numbers-");
			int n1=Integer.parseInt(br.readLine());
			int n2=Integer.parseInt(br.readLine());
			System.out.println("Addition of "+n1+" and "+n2+" is "+stub.add(n1,n2));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}