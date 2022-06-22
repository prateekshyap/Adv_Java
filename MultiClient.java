import java.net.*;
import java.io.*;

public class MultiClient {
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader (new InputStreamReader (System.in));
		Socket socket=new Socket("localhost",10001);
		DataInputStream dis=new DataInputStream (socket.getInputStream ());
		System.out.println (dis.readUTF ());
		DataOutputStream dos=new DataOutputStream (socket.getOutputStream ());
		int choice=Integer.parseInt (br.readLine ());
		dos.writeUTF(Integer.toString(choice));
		System.out.println(dis.readUTF());
		if (choice==1||choice==2) {
			int n1=Integer.parseInt(br.readLine());
			int n2=Integer.parseInt(br.readLine());
			dos.writeUTF(Integer.toString(n1));
			dos.writeUTF(Integer.toString(n2));
		}
		else if (choice==3) {
			int n=Integer.parseInt(br.readLine());
			dos.writeUTF(Integer.toString(n));
		}
		else if (choice==4) {
			int p=Integer.parseInt(br.readLine());
			double r=Double.parseDouble(br.readLine());
			int c=Integer.parseInt(br.readLine());
			int y=Integer.parseInt(br.readLine());
			dos.writeUTF(Integer.toString(p));
			dos.writeUTF(Double.toString(r));
			dos.writeUTF(Integer.toString(c));
			dos.writeUTF(Integer.toString(y));
		}
		try {
			System.out.println(dis.readUTF());
		}
		catch (Exception ee) {}
	}
}