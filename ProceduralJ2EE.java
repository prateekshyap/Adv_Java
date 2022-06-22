import java.sql.*;

public class ProceduralJ2EE
{
	public static void main(String[] args)throws Exception
	{
		Connection conn=null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try{
		conn=DriverManager.getConnection("jdbc:oracle:thin:@PRATEEKSHYA:1522:orcl","prateekshya","Soni19994$");
		//conn=DriverManager.getConnection("jdbc:oracle:thin:@prateekshya:1521:xe","system","soni1999");
		}
		catch(SQLRecoverableException se)
		{
			se.printStackTrace();
		}if(conn!=null)
			System.out.println("Connected!");
		else
			System.out.println("Not Connected!");
	}
}
/*import java.io.*;
import java.sql.*;
public class ProceduralJ2EE
{
	public static void main(String[] args)throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the first number-");
		int num1=Integer.parseInt(br.readLine());
		System.out.println("Enter the second number-");
		int num2=Integer.parseInt(br.readLine());
		int sum;
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@PRATEEKSHYA:1522:orcl","prateekshya","Soni19994$");
			CallableStatement cs=conn.prepareCall("{?=call addition(?,?)}");
			cs.setInt(2,num1);
			cs.setInt(3,num2);
			cs.registerOutParameter(1,Types.INTEGER);
			cs.execute();
			sum=cs.getInt(1);
			System.out.println(sum);
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}




import java.io.*;
import java.sql.*;
public class ProceduralJ2EE
{
	public static void main(String[] args)throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter a number-");
		int num1=Integer.parseInt(br.readLine());
		System.out.println("Enter another number-");
		int num2=Integer.parseInt(br.readLine());
		int large, small;
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		CallableStatement cs=conn.prepareCall("{call large_small(?,?,?,?)}");
		cs.setInt(1,num1);
		cs.setInt(2,num2);
		cs.registerOutParameter(3,Types.INTEGER);
		cs.registerOutParameter(4,Types.INTEGER);
		cs.execute();
		large=cs.getInt(3);
		small=cs.getInt(4);
		System.out.println("Largest number is "+large+" and the smallest one is "+small);
		cs.close();
		conn.close();
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
}*/