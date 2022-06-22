import java.net.*;
import java.io.*;

public class SumTwoServer {
	public static void main(String[] args) {
		try {
		ServerSocket serverSocket=new ServerSocket(1525);
		System.out.println("Server is waiting for input at port "+serverSocket.getLocalPort());
		Socket socket=serverSocket.accept();
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		int num1=Integer.parseInt(dis.readUTF());
		int num2=Integer.parseInt(dis.readUTF());
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		int digitsCounter1=0, digitsCounter2=0;
		int numCopy1=num1, numCopy2=num2;
		for(int i=0;numCopy1>0;i++) {
			numCopy1/=10;
			digitsCounter1++;
		}
		for(int i=0;numCopy2>0;i++) {
			numCopy2/=10;
			digitsCounter2++;
		}
		if(digitsCounter1!=digitsCounter2) {
			dos.writeUTF("Error!");
			System.exit(0);
		}
		numCopy1=num1;
		numCopy2=num2;
		int sumOfProducts=0;
		for(int i=0;numCopy1>0&&numCopy2>0;i++) {
			sumOfProducts+=((numCopy1%10)*(numCopy2%10));
			numCopy1/=10;
			numCopy2/=10;
		}
		dos.writeUTF("The sum of the corresponding digits is "+sumOfProducts);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}