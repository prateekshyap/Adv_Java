import java.net.*;
import java.io.*;

public class ClientIntro
{
	public static void main(String[] args)
	{
		try{
		Socket socket=new Socket("localhost",10000);
		System.out.println("Read the data from the socket-");
		InputStream is=socket.getInputStream();
		int ch;
		while((ch=is.read())!=-1)
			System.out.print((char)ch);
		}catch(Exception ee)
		{
		}
	}
}