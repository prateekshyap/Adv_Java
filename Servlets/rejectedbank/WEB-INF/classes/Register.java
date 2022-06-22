import java.io.*;
import javax.servlet.*;
import java.sql.*;

public class Register extends GenericServlet
{
	Connection conn;
	PreparedStatement ps;

	@Override
	public void init()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","soni1999");
			ps=conn.prepareStatement("insert into Customers values(?,?,?,?,?,?)");
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	@Override
	public void service(ServletRequest rq,ServletResponse rs)throws ServletException,IOException
	{
		try{
		rs.setContentType("text/html");
		PrintWriter pw=rs.getWriter();
		String name=rq.getParameter("user");
		String pass=rq.getParameter("pass1");
		long ph=Long.parseLong(rq.getParameter("phone"));
		String mailid=rq.getParameter("mail");
		long acc=Long.parseLong(rq.getParameter("accno"));
		double balance=0.0;
		ps.setString(1,name);
		ps.setString(2,pass);
		ps.setLong(3,ph);
		ps.setString(4,mailid);
		ps.setLong(5,acc);
		ps.setDouble(6,balance);
		int i=ps.executeUpdate();
		if(i!=0)
		{
			pw.println("<html><body bgcolor='yellow'><center>");
			pw.println("<h2>Successfully Registered!</h2>");
			pw.println("<h3>Click here to <a href='Login.html'>Login</a></h3>");
			pw.println("</center></body></html>");
		}
		else
		{
			pw.println("<html><body bgcolor='pink'><center>");
			pw.println("<h2>Unsuccessful registration</h2>");
			pw.println("<h3>Click here to <a href='Register.html'>Register</a> again</h3>");
			pw.println("</center></body></html>");
		}
		}catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}