import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() 
    {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter pw=response.getWriter();
		String name=request.getParameter("username");
		String pass=request.getParameter("password");
		Employee em=new Employee();
		em.setUsername(name);
		em.setPassword(pass);
		EmpDAO edao=new EmpDAOImpl();
		boolean i=edao.checkUser(em);
		pw.println("<html><body><center>");
		if(i)
		{
			pw.println("<font color='green' size='8'>Welcome to JT</font>");
		}
		else
		{
			pw.println("<font color='red' size='8'>Check your username and password!</font>");
		}
		pw.println("</center></body></html>");
	}
}
