import java.net.*;
import java.io.*;

public class FactorialClient {
	public static void main(String[] args) {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a number to find its factorial-");
			int num=Integer.parseInt(br.readLine());
			Socket socket=new Socket("localhost",1225);
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			String numStr=Integer.toString(num);
			dos.writeUTF(numStr);
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			System.out.println(dis.readUTF());
			socket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}