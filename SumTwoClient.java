import java.net.*;
import java.io.*;

public class SumTwoClient {
	public static void main(String[] args) {
		try {	
			Socket socket=new Socket("localhost",1525);
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter two numbers-");
			int num1=Integer.parseInt(br.readLine());
			int num2=Integer.parseInt(br.readLine());
			String num1String=Integer.toString(num1);
			String num2String=Integer.toString(num2);
			DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(num1String);
			dos.writeUTF(num2String);
			DataInputStream dis=new DataInputStream(socket.getInputStream());
			System.out.println(dis.readUTF());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}