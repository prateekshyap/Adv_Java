import java.net.*;

public class SocketIntro
{
	public static void main(String[] args)
	{
		try
		{
			InetAddress addr=InetAddress.getLocalHost();
			String name=addr.getHostName();
			String address=addr.getHostAddress();
			System.out.println("Name is "+name+" and Address is "+address);
		}
		catch(UnknownHostException uhe)
		{
			uhe.printStackTrace();
		}
	}
}