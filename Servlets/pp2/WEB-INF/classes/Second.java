import java.io.*;
import javax.servlet.*;

public class Second extends GenericServlet
{
	public Second()
	{
		System.out.println("Second class object is constructed!");
	}

	@Override
	public void init()
	{
		System.out.println("Second object is initialized!");
	}

	@Override
	public void service(ServletRequest rq, ServletResponse rs)throws IOException,ServletException
	{
		rs.setContentType("text/html");
		PrintWriter pw=rs.getWriter();
		pw.println("<html><body bgcolor='cyan'><font color='blue' size='5'><center>");
		pw.println("<b>I am in second page!</b>");
		pw.println("</center></font></body></html");
	}
}