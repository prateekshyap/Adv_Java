import java.rmi.*;
import java.rmi.server.*;
import java.sql.*;
import java.io.*;
import java.util.*;
import java.text.*;

public class FlightImplementation extends UnicastRemoteObject implements FlightInterface {
	Connection conn;

	public FlightImplementation () throws RemoteException {
		super ();
		try {
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}

	@Override
	public String getFlights (String src, String dest) {
		String str = "";
		try {
			PreparedStatement ps = conn.prepareStatement ("select s.code, d.code, f.code, f.departure_time, f.arrival_time, f.price, f.travel_time from Airport_Details s, Airport_Details d, Flight_Details f where s.name=? and d.name=? and f.source_code = s.code and f.destination_code = d.code union select s.code, d.code, f.code, f.departure_time, f.arrival_time, f.price, f.travel_time from Airport_Details s, Airport_Details d, Flight_Details f where s.name=? and d.name=? and f.source_code = s.code and f.destination_code = d.code");
			ps.setString (1,src);
			ps.setString (2,dest);
			ps.setString (3,dest);
			ps.setString (4,src);
			ResultSet rs = ps.executeQuery ();
			int i = 0;
			while (rs.next ())
			{
				str+=rs.getString (1);
				str+=" to ";
				str+=rs.getString (2);
				str+="-    ";
				str+=rs.getString (3);
				str+="\t";
				str+=rs.getString (4);
				str+="\t";
				str+=rs.getString (5);
				str+="\t";
				str+=rs.getString (6);
				str+="\t";
				str+=rs.getString (7);
				str+="%";
				str+=rs.getString (3);
				str+="#";
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		return str;
	}

	@Override
	public String getSeats (String jDate, String rDate, String jFlight, String rFlight) {
		int jRemaining = 0, rRemaining = 0;
		try {
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		java.util.Date ud = sdf.parse (jDate);
		java.sql.Date sqdj = new java.sql.Date (ud.getTime ());
		ud = sdf.parse (rDate);
		java.sql.Date sqdr = new java.sql.Date (ud.getTime ());
		PreparedStatement ps = conn.prepareStatement ("select remaining_seats from flight_booking_details where booked_date = ? and flight_code = ?");
		ps.setDate (1,sqdj);
		ps.setString (2,jFlight);
		ResultSet rs = ps.executeQuery ();
		if (rs.next ())
			jRemaining = rs.getInt (1);
		else
		{
			try
			{
				int totalSeats = 0;
				ps = conn.prepareStatement ("select total_seats from flight_details where code = ?");
				ps.setString (1,jFlight);
				rs = ps.executeQuery ();
				if (rs.next ())
					totalSeats = rs.getInt (1);
				ps = conn.prepareStatement ("insert into flight_booking_details values (?,?,?,?)");
				ps.setString (1,jFlight);
				ps.setDate (2,sqdj);
				ps.setInt (3,totalSeats);
				ps.setInt (4,totalSeats);
				int status = ps.executeUpdate ();
				if (status != 0)
					System.out.println (true);
				jRemaining = totalSeats;
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}
		ps = conn.prepareStatement ("select remaining_seats from flight_booking_details where booked_date = ? and flight_code = ?");
		ps.setDate (1,sqdr);
		ps.setString (2,rFlight);
		rs = ps.executeQuery ();
		if (rs.next ())
			rRemaining = rs.getInt (1);
		else
		{
			try
			{
				int totalSeats = 0;
				ps = conn.prepareStatement ("select total_seats from flight_details where code = ?");
				ps.setString (1,rFlight);
				rs = ps.executeQuery ();
				if (rs.next ())
					totalSeats = rs.getInt (1);
				ps = conn.prepareStatement ("insert into flight_booking_details values (?,?,?,?)");
				ps.setString (1,rFlight);
				ps.setDate (2,sqdr);
				ps.setInt (3,totalSeats);
				ps.setInt (4,totalSeats);
				int status = ps.executeUpdate ();
				if (status != 0)
					System.out.println (true);
				rRemaining = totalSeats;
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}}catch (Exception e) { e.printStackTrace (); }
		String jR = Integer.toString (jRemaining);
		String rR = Integer.toString (rRemaining);
		return (jR+"%"+rR);
	}

	@Override
	public String getSeat (String jDate, String jFlight) {
		int jRemaining = 0;
		try {
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		java.util.Date ud = sdf.parse (jDate);
		java.sql.Date sqdj = new java.sql.Date (ud.getTime ());
		PreparedStatement ps = conn.prepareStatement ("select remaining_seats from flight_booking_details where booked_date = ? and flight_code = ?");
		ps.setDate (1,sqdj);
		ps.setString (2,jFlight);
		ResultSet rs = ps.executeQuery ();
		if (rs.next ())
			jRemaining = rs.getInt (1);
		else
		{
			try
			{
				int totalSeats = 0;
				ps = conn.prepareStatement ("select total_seats from flight_details where code = ?");
				ps.setString (1,jFlight);
				rs = ps.executeQuery ();
				if (rs.next ())
					totalSeats = rs.getInt (1);
				ps = conn.prepareStatement ("insert into flight_booking_details values (?,?,?,?)");
				ps.setString (1,jFlight);
				ps.setDate (2,sqdj);
				ps.setInt (3,totalSeats);
				ps.setInt (4,totalSeats);
				int status = ps.executeUpdate ();
				if (status != 0)
					System.out.println (true);
				jRemaining = totalSeats;
			}
			catch (Exception e)
			{
				e.printStackTrace ();
			}
		}} catch (Exception e) { e.printStackTrace (); }
		String retStr = Integer.toString (jRemaining);
		return retStr;
	}

	@Override
	public String bookSeat (String name, String email, long phone, int age, String jFlight, String rFlight) {
		int bookingCode = 0, status = 0;
		try {
			Statement s = conn.createStatement ();
			ResultSet rs = s.executeQuery ("select booking_seq.nextval from dual");
			if (rs.next ())
				bookingCode = rs.getInt (1);
			PreparedStatement ps = conn.prepareStatement ("insert into customer_booking_details values (?,?,?,?,?,?,?)");
			ps.setInt (1,bookingCode);
			ps.setString (2,name);
			ps.setString (3,email);
			ps.setLong (4,phone);
			ps.setInt (5,age);
			ps.setString (6,jFlight);
			if (rFlight.equals (""))
				ps.setString (7,null);
			else
				ps.setString (7,rFlight);
			status = ps.executeUpdate ();
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		String retStr = "";
		String bc = Integer.toString (bookingCode);
		String st = Integer.toString (status);
		retStr += bc;
		retStr += "%";
		retStr += st;
		return retStr;
	}

	@Override
	public int getJPrice (String jFlight) {
		int jPrice = 0;
		try {
			PreparedStatement ps = conn.prepareStatement ("select price from flight_details where code = ?");
			ps.setString (1,jFlight);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
				jPrice = rs.getInt (1);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		return jPrice;
	}

	@Override
	public int getRPrice (String rFlight) {
		int rPrice = 0;
		try {
			PreparedStatement ps = conn.prepareStatement ("select price from flight_details where code = ?");
			ps.setString (1,rFlight);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
				rPrice = rs.getInt (1);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		return rPrice;
	}

	@Override
	public int payAmt (String bookingCode, long accNo, String bankName, int totalPrice) {
		int status = 0;
		try {
			PreparedStatement ps = conn.prepareStatement ("insert into payment_details values (?,?,?,?)");
			ps.setString (1,bookingCode);
			ps.setLong (2, accNo);
			ps.setString (3,bankName);
			ps.setDouble (4,totalPrice);
			status = ps.executeUpdate ();
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		return status;
	}

	@Override
	public void setJSeat (String jFlight) {
		try {
			PreparedStatement ps = conn.prepareStatement ("update flight_booking_details set remaining_seats = remaining_seats-1 where flight_code = ?");
			ps.setString (1,jFlight);
			ps.executeUpdate ();
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}

	@Override
	public void setRSeat (String rFlight) {
		try {
			PreparedStatement ps = conn.prepareStatement ("update flight_booking_details set remaining_seats = remaining_seats-1 where flight_code = ?");
			ps.setString (1,rFlight);
			ps.executeUpdate ();
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}

	@Override
	public String getCustDet (String bookCodeStr) {
		String custDet = "";
		try {
			PreparedStatement ps = conn.prepareStatement ("select * from cancellation_details where booking_code = ?");
			ps.setString (1,bookCodeStr);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ()) {
				custDet = "No history found!";
			}
			else {
				ps = conn.prepareStatement ("select * from customer_booking_details where booking_code = ?");
				ps.setString (1,bookCodeStr);
				rs = ps.executeQuery ();
				if (rs.next ())
					custDet = "<html>Name: "+rs.getString ("name")+"<br>Email Address: "+rs.getString ("email")+"<br>Phone: "+rs.getLong ("phone")+"<br>Age: "+rs.getInt ("age")+"<br><br>Confirm cancellation?</html>";
				else
					custDet = "No history found!";
			}
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
		return custDet;
	}

	@Override
	public void prepareCancellation (String bookCodeStr) {
		try {
			PreparedStatement ps = conn.prepareStatement ("insert into cancellation_details values (?)");
			ps.setString (1,bookCodeStr);
			ps.executeUpdate ();
			ps = conn.prepareStatement ("update flight_booking_details set remaining_seats = remaining_seats + 1 where flight_code in (select flight_code from customer_booking_details where booking_code = ?)");
			ps.setString (1,bookCodeStr);
			ps.executeUpdate ();
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}