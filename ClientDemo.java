import java.net.*;
import java.io.*;

public class ClientDemo {
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("localhost",1000);
			System.out.println("Connected to- "+socket.getRemoteSocketAddress()+" at port- "+socket.getPort());
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Hi Server!");
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			System.out.println("Server- "+dis.readUTF());
			socket.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}