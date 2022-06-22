import java.net.*;
import java.io.*;

public class ServerSeries2 {
	public static void main (String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(1231);
			System.out.println("Server is waiting for input at port "+serverSocket.getLocalPort());
			Socket socket=serverSocket.accept();
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			int x=Integer.parseInt(dis.readUTF());
			int n=Integer.parseInt(dis.readUTF());
			double sumOfSeries=1;
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			for (int i=2;i<=n;i++) {
				boolean isPrime=checkPrime(i);
				if (isPrime) {
					sumOfSeries+=Math.pow(x,i)/fact(i);
				}
			}
			dos.writeUTF("The sum of the series 1+(x^2/2!)+(x^3/3!)-(x^5/5!)+... is "+sumOfSeries);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean checkPrime (int rem) {
		int flag=0;
		boolean status=false;
		for(int i=2;i<=rem/2;i++)
			if(rem%i==0) {
				flag++;
				break;
			}
		if(flag==0)
			status=true;
		else
			status=false;
		return status;
	}
	public static int fact (int num) {
		int returnVal=1;
		for (int i=2;i<=num;i++)
			returnVal*=i;
		return returnVal;
	}
}