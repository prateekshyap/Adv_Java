import java.net.*;
import java.io.*;

public class Server {
	public static void main (String [] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket (1000);
		Socket socket = serverSocket.accept ();
		DataInputStream inputStream = new DataInputStream (socket.getInputStream ());
		DataOutputStream outputStream = new DataOutputStream (socket.getOutputStream ());
		int num = Integer.parseInt (inputStream.readUTF ());
		int copy = num;
		int rev = 0;
		while (copy > 0) {
			rev = rev*10 + copy%10;
			copy /= 10;
		}
		boolean status = false;
		if (num == rev)
			status = true;
		outputStream.writeUTF (Boolean.toString (status));
	}
}