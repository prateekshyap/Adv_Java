import java.net.*;
import java.io.*;

public class PrimeServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(1221);
			System.out.println("Server is waiting for input at port "+serverSocket.getLocalPort());
			Socket socket=serverSocket.accept();
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			int num=Integer.parseInt(dis.readUTF());
			int numCopy=num;
			int digitCounter=0, primeSum=0;
			for(int i=0;numCopy>0;i++) {
				int rem=numCopy%10;
				boolean isPrime=checkPrime(rem);
				if(isPrime)
					primeSum+=rem;
				numCopy/=10;
			}
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Sum of the prime digits is "+primeSum);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static boolean checkPrime(int rem) {
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
}