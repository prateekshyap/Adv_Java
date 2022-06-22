import java.sql.*;

public class EmpDAOImpl implements EmpDAO
{
	static boolean status;
	@Override
	public boolean checkUser(Employee ee) 
	{
		// TODO Auto-generated method stub
		try
		{
			Connection conn=DBConnect.getConnection();
			PreparedStatement ps=conn.prepareStatement("select * from LoginDel where username=? and password=?");
			ps.setString(1,ee.getUsername());
			ps.setString(2,ee.getPassword());
			ResultSet rs=ps.executeQuery();
			if(rs.next())
				status=true;
			else
				status=false;
			ps.close();
			conn.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return status;
	}
}