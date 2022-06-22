import javax.servlet.*;
import java.io.*;

public class InitParam extends GenericServlet
{
	ServletConfig sc=null;
	@Override
	public void init()
	{
		sc=getServletConfig();
	}

	@Override
	public void service(ServletRequest rq, ServletResponse rs)throws IOException,ServletException
	{
		rs.setContentType("text/html");
		PrintWriter pw=rs.getWriter();
		String n=sc.getInitParameter("name");
		String ph=sc.getInitParameter("phone");
		pw.println("<html><body bgcolor='cyan'><font color='red' size='5'><center>");
		pw.println("<b>Name is: "+n+"<br>Phone no: "+ph+"</b>");
		pw.println("</center></font></body></html>");
	}
}