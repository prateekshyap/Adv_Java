import java.net.*;
import java.io.*;

public class Client {
	public static void main (String [] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		Socket socket = new Socket ("localhost", 1000);
		DataInputStream inputStream = new DataInputStream (socket.getInputStream ());
		DataOutputStream outputStream = new DataOutputStream (socket.getOutputStream ());
		System.out.println ("Enter a number to find out whether it is a palindrome or not- ");
		int num = Integer.parseInt (br.readLine ());
		outputStream.writeUTF (Integer.toString (num));
		boolean result = Boolean.parseBoolean (inputStream.readUTF ());
		if (result == true)
			System.out.println ("Palindrome!");
		else
			System.out.println ("Not palindrome!");
	}
}