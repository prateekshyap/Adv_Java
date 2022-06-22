import java.io.*;
import java.net.*;

public class Client {
	public static void main (String [] args) throws IOException {
		Socket socket = new Socket ("localhost",2500);
		DataInputStream clientInput = new DataInputStream (socket.getInputStream ());
		DataOutputStream clientOutput = new DataOutputStream (socket.getOutputStream ());
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		System.out.println ("Enter a number to find out its factorial- ");
		String num = br.readLine ();
		clientOutput.writeUTF (num);
		System.out.println ("The factorial is- "+clientInput.readUTF ());
	}
}