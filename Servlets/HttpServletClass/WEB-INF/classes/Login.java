import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet
{
	Connection conn;
	PreparedStatement ps;

	public Login()
	{
		System.out.println("HttpServlet object is constructed!");
	}

	@Override
	public void init()
	{
		ServletConfig sc=getServletConfig();
		String d=sc.getInitParameter("driver");
		String url=sc.getInitParameter("url");
		String u=sc.getInitParameter("user");
		String p=sc.getInitParameter("pass");
		try
		{
			Class.forName(d);
			conn=DriverManager.getConnection(url,u,p);
			ps=conn.prepareStatement("select * from LoginDel where username=? and password=?");
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest hrequest, HttpServletResponse hresponse)throws IOException,ServletException
	{
		hresponse.setContentType("text/html");
		PrintWriter pw=hresponse.getWriter();
		String user=hrequest.getParameter("user");
		String password=hrequest.getParameter("pass");
		try
		{
			ps.setString(1,user);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{pw.println("<html><head><title>Successful</title><link rel='stylesheet' type='text/css' href='css/success.css'></head><body><p>You are an authorized user!</p></body></html>");
			}
			else
			{pw.println("<html><head><title>Failed!</title><link rel='stylesheet' type='text/css' href='css/failure.css'></head><body><p>Check your username and password!</p></body></html>");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	@Override
	public void destroy()
	{
		try
		{
			conn.close();
			ps.close();
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}