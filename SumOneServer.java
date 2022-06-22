import java.net.*;
import java.io.*;

public class SumOneServer {
	public static void main (String[] args) {
		try {
		ServerSocket serverSocket=new ServerSocket(1234);
		System.out.println("Server is waiting for input at port "+serverSocket.getLocalPort());
		Socket socket=serverSocket.accept();
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		int num=Integer.parseInt(dis.readUTF());
		int numCopy=num;
		int sumOfProducts=0;
		int rem=numCopy%10;
		for(int i=1;numCopy>0;i++) {
			numCopy/=10;
			int nextRem=numCopy%10;
			sumOfProducts+=(rem*nextRem);
			rem=nextRem;
		}
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		dos.writeUTF("The sum of the products of the consecutive digits is "+sumOfProducts);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
}
}