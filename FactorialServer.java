import java.net.*;
import java.io.*;

public class FactorialServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(1225);
			Socket socket=serverSocket.accept();
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			int number=Integer.parseInt(dis.readUTF());
			int fact=1;
			for(int i=number;i>1;i--)
				fact=fact*i;
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Factorial is "+fact);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}