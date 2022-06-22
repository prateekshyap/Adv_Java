import java.net.*;

public class SocketDemo
{
	public static void main(String[] args)
	{
		try{
		InetAddress addr=InetAddress.getLocalHost();
		String hostName=addr.getHostName();
		String hostAddress=addr.getHostAddress();
		System.out.println("The host name is- "+hostName+" and the host address is- "+hostAddress);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}