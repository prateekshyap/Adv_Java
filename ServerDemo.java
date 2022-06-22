import java.io.*;
import java.net.*;

public class ServerDemo	{
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket=new ServerSocket(1000);
			System.out.println("Server is waiting at port number- "+serverSocket.getLocalPort());
			Socket socket=serverSocket.accept();
			System.out.println("Got one client- "+socket.getRemoteSocketAddress());
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			System.out.println(socket.getRemoteSocketAddress()+"- "+dis.readUTF());	
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Hi client!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}