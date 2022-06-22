import java.net.*;
import java.io.*;

public class PrimeClient {
	public static void main(String[] args) {
		try {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Socket socket=new Socket("localhost",1221);
		System.out.println("Enter a number- ");
		int num=Integer.parseInt(br.readLine());
		DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
		String numString=Integer.toString(num);
		dos.writeUTF(numString);
		DataInputStream dis=new DataInputStream(socket.getInputStream());
		System.out.println(dis.readUTF());
		socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}