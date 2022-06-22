import java.sql.*;

public class DBConnect
{
	static Connection conn;

	public static Connection getConnection()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn;
	}
}