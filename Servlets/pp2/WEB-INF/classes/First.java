import java.io.*;
import javax.servlet.*;

public class First extends GenericServlet
{

	@Override
	public void init()
	{
		System.out.println("First servlet is initialized!");
	}

	public First()
	{
		System.out.println("First servlet object is constructed!");
	}

	@Override
	public void service(ServletRequest rq,ServletResponse rs)throws IOException,ServletException
	{
		rs.setContentType("text/html");
		PrintWriter pw=rs.getWriter();
		pw.println("<html><body bgcolor='cyan'><font color='red' size='5'><center>");
		pw.println("<b>I am in the First page</b><br>");
		pw.println("<a href='http://localhost:1200/pp2/second'>next_page</a>");
		pw.println("</center></font></body></html>");
	}
}