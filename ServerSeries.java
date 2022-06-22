import java.net.*;
import java.io.*;
//import java.lang.Math;

public class ServerSeries {
	public static void main (String[] args) {
		try {
		ServerSocket serverSocket=new ServerSocket(1212);
		System.out.println("Server is waiting for input at port "+serverSocket.getLocalPort());
		Socket socket=serverSocket.accept();
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		int x=Integer.parseInt(dis.readUTF());
		int n=Integer.parseInt(dis.readUTF());
		double sumOfSeries=1;
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		for (int i=2;i<=n;i+=2) {
			if (i%4==0) {
				sumOfSeries+=Math.pow(x,i)/fact(i);
			}
			else if (i%4==2) {
				sumOfSeries-=Math.pow(x,i)/fact(i);
			}
			else {
				dos.writeUTF("Error!");
				System.exit(0);
			}
		}
		dos.writeUTF("The sum of the series 1-(x^2/2!)+(x^4/4!)-(x^6/6!)+... is "+sumOfSeries);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static int fact (int num) {
		int returnVal=1;
		for (int i=2;i<=num;i++)
			returnVal*=i;
		return returnVal;
	}
}