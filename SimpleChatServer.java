import java.io.*;
import java.net.*;

public class SimpleChatServer {
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		ServerSocket serverSocket=new ServerSocket (12000);
		System.out.println ("Chat application is ready!\nHit enter to send a message.\nEnter \"quit\" to close the chat application.");
		Socket socket=serverSocket.accept();
		DataInputStream serverInputStream=new DataInputStream (socket.getInputStream());
		DataOutputStream serverOutputStream=new DataOutputStream (socket.getOutputStream());
		while (true) {
			try {
				String serverMessage=br.readLine();
				//System.out.println ("sent> "+serverMessage);
				serverOutputStream.writeUTF (serverMessage);
				if (serverMessage.equalsIgnoreCase ("quit"))
					System.exit (0);
				String receivedMessage=serverInputStream.readUTF();
				if (receivedMessage.equalsIgnoreCase ("quit"))
					System.exit (0);
				else
					System.out.println ("received> "+receivedMessage);
			}
			catch (Exception e) {
				e.printStackTrace ();
			}
		}
	}
}