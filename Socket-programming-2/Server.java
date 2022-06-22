import java.net.*;
import java.io.*;

public class Server {
	public static void main (String [] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket (2500);
		Socket socket = serverSocket.accept ();
		DataInputStream serverInput = new DataInputStream (socket.getInputStream ());
		DataOutputStream serverOutput = new DataOutputStream (socket.getOutputStream ());
		System.out.println ("Server is waiting at port- ");
		int num = Integer.parseInt (serverInput.readUTF ());
		int fact = 1;
		for (int i = num ; i > 1 ; i--)
			fact *= i;
		serverOutput.writeUTF (Integer.toString (fact));
	}
}