import java.io.*;
import javax.servlet.*;
import java.sql.*;

public class Login implements Servlet
{
	Connection conn;
	PreparedStatement ps;
	public Login()
	{
		System.out.println("Servlet class is instantiated!");
	}

	@Override
	public void init(ServletConfig sc)throws ServletException
	{
		System.out.println("Servlet object is initialized!");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
			ps=conn.prepareStatement("select * from LoginDel where username=? and password=?");
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	@Override
	public String getServletInfo()
	{
		return "hello";
	}

	@Override
	public ServletConfig getServletConfig()
	{
		return this.getServletConfig();
	}

	@Override
	public void service(ServletRequest request,ServletResponse response)throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String u=request.getParameter("user");
		String p=request.getParameter("pass");
		try
		{
			ps.setString(1,u);
			ps.setString(2,p);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				//pw.println("<html><head><title>Successful</title><link rel='stylesheet' type='text/css' href='success.css'></head><body><p>You are an authorized user!</p></body></html>");
				pw.println("<html><head><title>Successful</title><link rel='stylesheet' type='text/css' href='css/success.css'></head><body><p>You are an authorized user!</p></body></html>");
			}
			else
			{
				pw.println("<html><head><title>Failed!</title><link rel='stylesheet' type='text/css' href='css/failure.css'></head><body><p>Check your username and password!</p></body></html>");
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
			ps.close();
			conn.close();
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}