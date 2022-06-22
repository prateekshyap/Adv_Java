import java.rmi.*;
import java.sql.*;
import java.util.*;

public interface FlightInterface extends Remote {
	public String getFlights (String source, String destination) throws RemoteException;
	public String getSeats (String jDate, String rDate, String jFlight, String rFlight) throws RemoteException;
	public String getSeat (String jDate, String jFlight) throws RemoteException;
	public String bookSeat (String name, String email, long phone, int age, String jFlight, String rFlight) throws RemoteException;
	public int getJPrice (String jFlight) throws RemoteException;
	public int getRPrice (String rFlight) throws RemoteException;
	public int payAmt (String bookingCode, long accNo, String bankName, int totalPrice) throws RemoteException;
	public void setJSeat (String jFlight) throws RemoteException;
	public void setRSeat (String rFlight) throws RemoteException;
	public String getCustDet (String bookCodeStr) throws RemoteException;
	public void prepareCancellation (String bookCodeStr) throws RemoteException;
}