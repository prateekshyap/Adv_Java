import java.io.*;
import java.sql.*;
import javax.servlet.*;
public class Login implements Servlet
{
	Connection conn;
	PreparedStatement ps;
	@Override
	public void init(ServletConfig sc)throws ServletException
	{
		System.out.println("Servlet is initialized!");
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		ps=conn.prepareStatement("select * from MyLogin where un=? and pw=?");
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
	@Override
	public void destroy()
	{
		System.out.println("Servlet object is finalized!");
	}
	@Override
	public String getServletInfo()
	{
		return "Prateekshya";
	}
	@Override
	public ServletConfig getServletConfig()
	{
		return this.getServletConfig();
	}
	@Override
	public void service(ServletRequest req,ServletResponse res)throws ServletException,IOException
	{
		try{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String u=req.getParameter("name");
		String p=req.getParameter("pass");
		ps.setString(1,u);
		ps.setString(2,p);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			out.println("<html><body color= 'pink'><font color= 'red' size='5'><center><b>Welcome to My Application!</b></center></font></body></html>");
		}
		else
		{
			out.println("<html><body bgcolor='yellow'> <font color= 'blue' size= '5'><center><b>Check your username and password!</b></center></font></body></html>");
		}
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}