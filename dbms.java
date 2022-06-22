import java.io.*;
import java.sql.*;

public class dbms {
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
		System.out.println ("Enter the registration number- ");
		long regdNo = Long.parseLong (br.readLine ());
		String name = "", branch = "";
		Long roll = (long) 0, phone = (long) 0;
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
			PreparedStatement ps = conn.prepareStatement ("select * from igit_servlet_table where regdno = ?");
			ps.setLong (1, regdNo);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ()) {
				roll = rs.getLong ("rollno");
				name = rs.getString ("name");
				branch = rs.getString ("branch");
				phone = rs.getLong ("phone");
			}
			rs.close ();
			ps.close ();
			conn.close ();
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		if (name.equals (""))
			System.out.println ("Not Available!");
		else
			System.out.println ("NAME: "+name+"ROLL NO: "+roll+"BRANCH: "+branch+"PHONE: "+phone);
	}
}