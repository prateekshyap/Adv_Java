import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int num1=Integer.parseInt(br.readLine());
		int num2=Integer.parseInt(br.readLine());
		int n1=num1;
		int n2=num2;
		while(num1!=num2) {
			if(num1>num2)
				num1=num1-num2;
			else
				num2=num2-num1;
		}
		System.out.println((n1*n2)/num1);
		System.out.println("\"");
	}
}

/*import java.io.*;

public class Test {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int num1=Integer.parseInt(br.readLine());
		int num2=Integer.parseInt(br.readLine());
		while(num1!=num2) {
			if(num1>num2)
				num1=num1-num2;
			else
				num2=num2-num1;
		}
		System.out.println(num1);
	}
}*/

/*import java.io.*;
import java.sql.*;
public class Test
{
	static Connection connect;
	static Statement s;
	static PreparedStatement ps;
	public static void main(String[] args)throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connect=DriverManager.getConnection("jdbc:oracle:thin:@prateekshya:1522:orcl","system","soni1999");
			if(connect==null)
			{
				System.out.println("Connection couldn't be established.");
				System.exit(0);
			}
			s=connect.createStatement();
			s.executeUpdate("create table mytab (rollno number(5), regdno number(11), name varchar2(30), branch varchar2(30))");
			ps=connect.prepareStatement("insert into mytab values(?,?,?,?)");
			for(int i=0;i<3;i++)
			{
				System.out.println("Enter the roll number-");
				int roll=Integer.parseInt(br.readLine());
				System.out.println("Enter the registration number-");
				int regd=Integer.parseInt(br.readLine());
				System.out.println("Enter the name-");
				String name=br.readLine();
				System.out.println("Enter the branch-");
				String branch=br.readLine();
				ps.setInt(1,roll);
				ps.setInt(2,regd);
				ps.setString(3,name);
				ps.setString(4,branch);
				ps.executeUpdate();
			}
			s=connect.createStatement();
			ResultSet rs=s.executeQuery("select * from mytab");
			int i=1;
			while(rs.next())
			{
				System.out.println("Student number: "+i);
				System.out.println("NAME: "+rs.getString(3));
				System.out.println("ROLL NO: "+rs.getInt(1));
				System.out.println("REGD NO: "+rs.getInt(2));
				System.out.println("BRANCH: "+rs.getString(4));
				System.out.println();
				i++;
			}
		}catch(ClassNotFoundException ce)
		{
			System.out.println("Class not found");
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		finally
		{
			try{
				s.close();
				ps.close();
				connect.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
}*/