import java.io.*;
import java.net.*;

public class SimpleChatClient {
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		Socket socket=new Socket ("localhost",12000);
		System.out.println ("Chat application is ready!\nHit enter to send a message.\nEnter \"quit\" to close the chat application.");
		DataInputStream clientInputStream=new DataInputStream (socket.getInputStream());
		DataOutputStream clientOutputStream=new DataOutputStream (socket.getOutputStream());
		while (true) {
			try {				
				String receivedMessage=clientInputStream.readUTF();
				if (receivedMessage.equalsIgnoreCase ("quit"))
					System.exit (0);
				else
					System.out.println ("received> "+receivedMessage);
				String clientMessage=br.readLine();
				//System.out.println ("sent> "+clientMessage);
				clientOutputStream.writeUTF (clientMessage);
				if (clientMessage.equalsIgnoreCase ("quit"))
					System.exit (0);
			}
			catch (Exception e) {
				e.printStackTrace ();
			}
		}
	}
}