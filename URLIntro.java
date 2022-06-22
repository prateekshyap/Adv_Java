import java.net.*;
import java.io.*;

public class URLIntro
{
	public static void main(String[] args)
	{
		try
		{
			URL u=new URL("http://www.google.com");
			int ch;
			InputStream is=u.openStream();
			while((ch=is.read())!=-1)
				System.out.print(ch);
			is.close();
		}
		catch(Exception me)
		{
			me.printStackTrace();
		}
	}
}