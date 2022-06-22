import java.net.*;
import java.io.*;

public class SumOneClient {
	public static void main(String[] args) {
		try {
			Socket socket=new Socket("localhost",1234);
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter a number-");
			int num=Integer.parseInt(br.readLine());
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			String numStr=Integer.toString(num);
			dos.writeUTF(numStr);
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			System.out.println(dis.readUTF());
			socket.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}