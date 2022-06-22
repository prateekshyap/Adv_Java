import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;

class MainBook extends JFrame implements ItemListener
{
	static Container container;
	static JPanel mainPanel;
	static JCheckBox zeroFive, fiveTwelve, twelveMore, returnJourney;
	static JComboBox sourceBox, destinationBox, flightBox1, flightBox2;
	static JTextArea flightData1, flightData2;
	static JButton search, next, cancel;
	static JTextField journeyField, returnField;
	static JLabel journeyLabel, returnLabel;
	static Font f1, f2;
	static Connection conn;
	MainBook ()
	{
		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);
		f2 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//checkboxes
		zeroFive = new JCheckBox ("0-5 years");
		zeroFive.setBounds (200-50+700,50+15,125,35);
		zeroFive.setFont (f1);
		zeroFive.addItemListener (this);

		fiveTwelve = new JCheckBox ("5-12 years");
		fiveTwelve.setBounds (450-50-100-25+700,50+15,125,35);
		fiveTwelve.setFont (f1);
		fiveTwelve.addItemListener (this);

		twelveMore = new JCheckBox ("Adults");
		twelveMore.setBounds (700-50-200-50+700,50+15,125,35);
		twelveMore.setFont (f1);
		twelveMore.addItemListener (this);

		returnJourney = new JCheckBox ("Return journey");
		returnJourney.setBounds (300,50+15,200,35);
		returnJourney.setFont (f1);
		returnJourney.addItemListener (this);

		//comboboxes
		sourceBox = new JComboBox ();
		sourceBox.addItem ("--Select Source--");
		sourceBox.addItem ("Bangalore, IN- Kempegowda International Airport");
		sourceBox.addItem ("New Delhi, IN- Indira Gandhi Airport");
		sourceBox.addItem ("Hyderabad, IN- Rajiv Gandhi International");
		sourceBox.addItem ("Kolkata, IN- Netaji Subhas Chandra Bose Airport");
		sourceBox.addItem ("Chennai, IN- Chennai Airport");
		sourceBox.addItem ("Mumbai IN- Chatrapati Shivaji Airport");
		sourceBox.setBounds (5,5,600,35);
		sourceBox.setFont (f1);
		sourceBox.addItemListener (this);

		destinationBox = new JComboBox ();
		destinationBox.addItem ("--Select Destination--");
		destinationBox.addItem ("Bangalore, IN- Kempegowda International Airport");
		destinationBox.addItem ("New Delhi, IN- Indira Gandhi Airport");
		destinationBox.addItem ("Hyderabad, IN- Rajiv Gandhi International");
		destinationBox.addItem ("Kolkata, IN- Netaji Subhas Chandra Bose Airport");
		destinationBox.addItem ("Chennai, IN- Chennai Airport");
		destinationBox.addItem ("Mumbai IN- Chatrapati Shivaji Airport");
		destinationBox.setBounds (630,5,600,35);
		destinationBox.setFont (f1);
		destinationBox.setEnabled (false);

		flightBox1 = new JComboBox ();
		flightBox1.setBounds (5,450,600,35);
		flightBox1.setFont (f1);

		flightBox2 = new JComboBox ();
		flightBox2.setBounds (630,450,600,35);
		flightBox2.setFont (f1);

		//textareas
		flightData1 = new JTextArea ();
		flightData1.setBounds (5,200,600,200);
		flightData1.setFont (f2);
		flightData1.setEditable (false);
		flightData1.setLineWrap(true);
		flightData1.setWrapStyleWord(true);
		flightData1.setRows (100);
		flightData1.setColumns (100);

		flightData2 = new JTextArea ();
		flightData2.setBounds (630,200,600,200);
		flightData2.setFont (f2);
		flightData2.setEditable (false);
		flightData2.setLineWrap(true);
		flightData2.setWrapStyleWord(true);
		flightData2.setRows (100);
		flightData2.setColumns (100);

		//textfields
		journeyField = new JTextField ();
		journeyField.setFont (f1);
		journeyField.setBounds (125,50+15,150,35);

		returnField = new JTextField ();
		returnField.setFont (f1);
		returnField.setBounds (625,50+15,150,35);

		//labels
		journeyLabel = new JLabel ("Journey Date");
		journeyLabel.setFont (f1);
		journeyLabel.setBounds (5,50+15,200,35);

		returnLabel = new JLabel ("Return Date");
		returnLabel.setFont (f1);
		returnLabel.setBounds (515,50+15,200,35);

		//buttons
		search = new JButton ("Search Flights");
		search.setBounds (525,125,200,50);
		search.setFont (f1);
		search.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				searchAction (ae);
			}
		});

		next = new JButton ("Book");
		next.setBounds (525,500,200,50);
		next.setFont (f1);
		next.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				nextAction (ae);
			}
		});

		cancel = new JButton ("Cancel Ticket");
		cancel.setBounds (525,600,200,50);
		cancel.setFont (f1);
		cancel.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					cancelAction (ae);
				}
			});

		//main panel
		mainPanel = new JPanel ();
		mainPanel.setLayout (null);
		mainPanel.setBounds (0,0,1250,700);

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (zeroFive);
		mainPanel.add (fiveTwelve);
		mainPanel.add (twelveMore);
		mainPanel.add (returnJourney);
		mainPanel.add (sourceBox);
		mainPanel.add (destinationBox);
		mainPanel.add (search);
		mainPanel.add (journeyField);
		mainPanel.add (journeyLabel);
		mainPanel.add (cancel);

		try 
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	//actionlistener for search button
	public void searchAction (ActionEvent ae)
	{
		String src = (String) sourceBox.getSelectedItem ();
		String dest = (String) destinationBox.getSelectedItem ();
		mainPanel.add (flightData1);
		mainPanel.add (flightBox1);
		if (returnJourney.isSelected ())
		{
			mainPanel.add (flightData2);
			mainPanel.add (flightBox2);
		}
		else
		{
			mainPanel.remove (flightData2);
			mainPanel.remove (flightBox2);
		}
		mainPanel.revalidate ();
		mainPanel.repaint ();
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select s.code, d.code, f.code, f.departure_time, f.arrival_time, f.price, f.travel_time from Airport_Details s, Airport_Details d, Flight_Details f where s.name=? and d.name=? and f.source_code = s.code and f.destination_code = d.code union select s.code, d.code, f.code, f.departure_time, f.arrival_time, f.price, f.travel_time from Airport_Details s, Airport_Details d, Flight_Details f where s.name=? and d.name=? and f.source_code = s.code and f.destination_code = d.code");
			ps.setString (1,src);
			ps.setString (2,dest);
			ps.setString (3,dest);
			ps.setString (4,src);
			ResultSet rs = ps.executeQuery ();
			flightData1.setText ("");
			flightData2.setText ("");
			int counter = 0;
			flightData1.setText ("\tFlight Code\tDeparture\tArrival\tPrice\tDuration\n");
			flightData2.setText ("\tFlight Code\tDeparture\tArrival\tPrice\tDuration\n");
			flightBox1.removeAllItems ();
			flightBox1.addItem ("--Select Flight--");
			flightBox2.removeAllItems ();
			flightBox2.addItem ("--Select Flight--");
			while (rs.next ())
			{
				counter++;
				if (counter <= 5)
				{
					flightData1.append (rs.getString (1)+" to "+rs.getString (2)+"-    "+rs.getString (3)+"\t"+rs.getString (4)+"\t"+rs.getString (5)+"\t"+rs.getString (6)+"\t"+rs.getString (7)+"\n");
					flightBox1.addItem (rs.getString (3));
				}
				else
				{
					flightData2.append (rs.getString (1)+" to "+rs.getString (2)+"-    "+rs.getString (3)+"\t"+rs.getString (4)+"\t"+rs.getString (5)+"\t"+rs.getString (6)+"\t"+rs.getString (7)+"\n");
					flightBox2.addItem (rs.getString (3));
				}
			}
			sourceBox.setEnabled (false);
			destinationBox.setEnabled (false);
			mainPanel.add (next);
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	//actionlistener for next button
	public void nextAction (ActionEvent ae)
	{
		try{
		if (returnJourney.isSelected ())
		{
			String jDate = journeyField.getText ();
			String rDate = returnField.getText ();
			SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
			java.util.Date ud = sdf.parse (jDate);
			java.sql.Date sqdj = new java.sql.Date (ud.getTime ());
			ud = sdf.parse (rDate);
			java.sql.Date sqdr = new java.sql.Date (ud.getTime ());
			String jFlight = (String) flightBox1.getSelectedItem ();
			String rFlight = (String) flightBox2.getSelectedItem ();
			PreparedStatement ps = conn.prepareStatement ("select remaining_seats from flight_booking_details where booked_date = ? and flight_code = ?");
			ps.setDate (1,sqdj);
			ps.setString (2,jFlight);
			int jRemaining = 0, rRemaining = 0;
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
			}
			dispose ();
			setVisible (false);
			BookPage bp = new BookPage (jRemaining, rRemaining, jFlight, rFlight);
			bp.setVisible (true);
			bp.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			bp.setBounds (0,0,1250,700);
		}
		else
		{
			String jDate = journeyField.getText ();
			SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
			java.util.Date ud = sdf.parse (jDate);
			java.sql.Date sqdj = new java.sql.Date (ud.getTime ());
			String jFlight = (String) flightBox1.getSelectedItem ();
			PreparedStatement ps = conn.prepareStatement ("select remaining_seats from flight_booking_details where booked_date = ? and flight_code = ?");
			ps.setDate (1,sqdj);
			ps.setString (2,jFlight);
			int jRemaining = 0;
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
			dispose ();
			setVisible (false);
			BookPage bp = new BookPage (jRemaining, jFlight);
			bp.setVisible (true);
			bp.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
			bp.setBounds (0,0,1250,700);
		}
	}catch (Exception e)
	{
		e.printStackTrace ();
	}
	}

	//itemlistener
	public void itemStateChanged (ItemEvent ie)
	{
		String src = (String) sourceBox.getSelectedItem ();
		if (src.equals ("--Select Source--"))
		{
			destinationBox.setEnabled (false);
			destinationBox.setSelectedItem ("--Select Destination--");
		}
		else
		{
			String dstn = (String) destinationBox.getSelectedItem ();
			if (dstn.equals ("--Select Destination--"))
			{
				destinationBox.removeAllItems ();
				destinationBox.addItem ("--Select Destination--");
				destinationBox.addItem ("Bangalore, IN- Kempegowda International Airport");
				destinationBox.addItem ("New Delhi, IN- Indira Gandhi Airport");
				destinationBox.addItem ("Hyderabad, IN- Rajiv Gandhi International");
				destinationBox.addItem ("Kolkata, IN- Netaji Subhas Chandra Bose Airport");
				destinationBox.addItem ("Chennai, IN- Chennai Airport");
				destinationBox.addItem ("Mumbai IN- Chatrapati Shivaji Airport");
				destinationBox.removeItem (src);
				destinationBox.setSelectedItem ("--Select Destination");
				destinationBox.setEnabled (true);
			}
		}
		if (returnJourney.isSelected ())
		{
			mainPanel.add (returnLabel);
			mainPanel.add (returnField);
			mainPanel.revalidate ();
			mainPanel.repaint ();
		}
		else
		{
			mainPanel.remove (returnLabel);
			mainPanel.remove (returnField);
			mainPanel.revalidate ();
			mainPanel.repaint ();
		}
	}

	//actionlistener for cancel button
	public void cancelAction (ActionEvent ae)
	{
		dispose ();
		setVisible (false);
		Cancellation c = new Cancellation ();
		c.setVisible (true);
		c.setBounds (0,0,1250,700);
		c.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
}

class BookPage extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static Font f1, f2;
	static JLabel flightLabelJ, flightLabelR, name, age, email, phone;
	static int waiting = 0;
	static JTextField nameTf, ageTf, emailTf, phoneTf;
	static JButton proceed;
	static Connection conn;
	static String jFlight = "", rFlight = "";
	BookPage (int jRemaining, int rRemaining, String jFlight, String rFlight)
	{
		this.jFlight = jFlight;
		this.rFlight = rFlight;

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);
		f2 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//main panel
		mainPanel = new JPanel ();
		mainPanel.setLayout (null);
		mainPanel.setBounds (0,0,1250,700);

		//labels
		flightLabelJ = new JLabel ();
		if (jRemaining > 0)
			flightLabelJ.setText ("Availability: AVAILABLE"+jRemaining);
		else
		{
			waiting = -jRemaining;
			flightLabelJ.setText ("Availability: WAITING"+waiting);
		}
		flightLabelJ.setBounds (50,50,500,35);
		flightLabelJ.setFont (f1);

		flightLabelR = new JLabel ();
		if (rRemaining > 0)
			flightLabelR.setText ("Availability: AVAILABLE"+rRemaining);
		else
		{
			waiting = -rRemaining;
			flightLabelR.setText ("Availability: WAITING"+waiting);
		}
		flightLabelR.setBounds (700,50,500,35);
		flightLabelR.setFont (f1);


		name = new JLabel ("Name");
		name.setFont (f1);
		name.setBounds (50,150,150,35);

		age = new JLabel ("Age");
		age.setFont (f1);
		age.setBounds (625,150,75,35);

		email = new JLabel ("Email Address");
		email.setFont (f1);
		email.setBounds (50,225,150,35);

		phone = new JLabel ("Phone");
		phone.setFont (f1);
		phone.setBounds (625,225,75,35);

		//textfields
		nameTf = new JTextField ();
		nameTf.setFont (f1);
		nameTf.setBounds (225,150,300,35);

		ageTf = new JTextField ();
		ageTf.setFont (f1);
		ageTf.setBounds (750,150,300,35);

		emailTf = new JTextField ();
		emailTf.setFont (f1);
		emailTf.setBounds (225,225,300,35);
		
		phoneTf = new JTextField ();
		phoneTf.setFont (f1);
		phoneTf.setBounds (750,225,300,35);

		//buttons
		proceed = new JButton ("Proceed to Payment");
		proceed.setFont (f1);
		proceed.setBounds (475,300,300,50);
		proceed.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					proceedAction (ae);
				}
			});

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (flightLabelJ);
		mainPanel.add (flightLabelR);
		mainPanel.add (name);
		mainPanel.add (age);
		mainPanel.add (email);
		mainPanel.add (phone);
		mainPanel.add (nameTf);
		mainPanel.add (ageTf);
		mainPanel.add (emailTf);
		mainPanel.add (phoneTf);
		mainPanel.add (proceed);

		try
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	BookPage (int jRemaining, String jFlight)
	{
		this.jFlight = jFlight;

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);
		f2 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//main panel
		mainPanel = new JPanel ();
		mainPanel.setLayout (null);
		mainPanel.setBounds (0,0,1250,700);

		//labels
		flightLabelJ = new JLabel ();
		if (jRemaining > 0)
			flightLabelJ.setText ("Availability: AVAILABLE"+jRemaining);
		else
		{
			waiting = -jRemaining;
			flightLabelJ.setText ("Availability: WAITING"+waiting);
		}
		flightLabelJ.setBounds (50,50,500,35);
		flightLabelJ.setFont (f1);

		//adding the main panel to the container
		container.add (mainPanel);

		//labels
		name = new JLabel ("Name");
		name.setFont (f1);
		name.setBounds (50,150,150,35);

		age = new JLabel ("Age");
		age.setFont (f1);
		age.setBounds (625,150,75,35);

		email = new JLabel ("Email Address");
		email.setFont (f1);
		email.setBounds (50,225,150,35);

		phone = new JLabel ("Phone");
		phone.setFont (f1);
		phone.setBounds (625,225,75,35);

		//textfields
		nameTf = new JTextField ();
		nameTf.setFont (f1);
		nameTf.setBounds (225,150,300,35);

		ageTf = new JTextField ();
		ageTf.setFont (f1);
		ageTf.setBounds (750,150,300,35);

		emailTf = new JTextField ();
		emailTf.setFont (f1);
		emailTf.setBounds (225,225,300,35);
		
		phoneTf = new JTextField ();
		phoneTf.setFont (f1);
		phoneTf.setBounds (750,225,300,35);

		//buttons
		proceed = new JButton ("Proceed to Payment");
		proceed.setFont (f1);
		proceed.setBounds (475,300,300,50);
		proceed.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					proceedAction (ae);
				}
			});

		//adding the components to the main panel
		mainPanel.add (flightLabelJ);
		mainPanel.add (name);
		mainPanel.add (age);
		mainPanel.add (email);
		mainPanel.add (phone);
		mainPanel.add (nameTf);
		mainPanel.add (ageTf);
		mainPanel.add (emailTf);
		mainPanel.add (phoneTf);
		mainPanel.add (proceed);

		try
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	public void proceedAction (ActionEvent ae)
	{
		try
		{
			int bookingCode = 0;
			Statement s = conn.createStatement ();
			ResultSet rs = s.executeQuery ("select booking_seq.nextval from dual");
			if (rs.next ())
				bookingCode = rs.getInt (1);
			PreparedStatement ps = conn.prepareStatement ("insert into customer_booking_details values (?,?,?,?,?,?,?)");
			ps.setInt (1,bookingCode);
			ps.setString (2,nameTf.getText ());
			ps.setString (3,emailTf.getText ());
			ps.setLong (4,Long.parseLong (phoneTf.getText ()));
			ps.setInt (5,Integer.parseInt (ageTf.getText ()));
			ps.setString (6,jFlight);
			if (rFlight.equals (""))
				ps.setString (7,null);
			else
				ps.setString (7,rFlight);
			int status = ps.executeUpdate ();
			if (status != 0)
			{
				dispose ();
				setVisible (false);
				Payment p = new Payment (jFlight, rFlight, bookingCode);
				p.setVisible (true);
				p.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				p.setBounds (0,0,1250,700);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
}

class Payment extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static Font f1, f2;
	static JLabel accNoL, bankNameL, priceL;
	static JTextField accNoTf, bankNameTf;
	static JButton book;
	static Connection conn; 
	static int jPrice = 0, rPrice = 0;
	static String jFlight = "", rFlight = "";
	static String bookingCode = "";
	Payment (String jFlight, String rFlight, int bookingCode)
	{
		this.bookingCode = Integer.toString (bookingCode);
		this.jFlight = jFlight;
		this.rFlight = rFlight;

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);
		f2 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//main panel
		mainPanel = new JPanel ();
		mainPanel.setLayout (null);
		mainPanel.setBounds (0,0,1250,700);

		try
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
			PreparedStatement ps = conn.prepareStatement ("select price from flight_details where code = ?");
			ps.setString (1,jFlight);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
				jPrice = rs.getInt (1);
			if (!rFlight.equals (""))
			{
				ps = conn.prepareStatement ("select price from flight_details where code = ?");
				ps.setString (1,rFlight);
				rs = ps.executeQuery ();
				if (rs.next ())
					rPrice = rs.getInt (1);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}

		//labels
		priceL = new JLabel ();
		if (rPrice != 0)
			priceL.setText ("Total Price: "+jPrice+"                                                            Total Price: "+rPrice);
		else
			priceL.setText ("Total Price: "+jPrice);
		priceL.setFont (f1);
		priceL.setBounds (50,50,800,35);

		accNoL = new JLabel ("Account Number");
		accNoL.setFont (f1);
		accNoL.setBounds (50,125,200,35);

		bankNameL = new JLabel ("Bank Name");
		bankNameL.setFont (f1);
		bankNameL.setBounds (600,125,150,35);

		//textfields
		accNoTf = new JTextField ();
		accNoTf.setFont (f1);
		accNoTf.setBounds (250,125,300,35);

		bankNameTf = new JTextField ();
		bankNameTf.setFont (f1);
		bankNameTf.setBounds (775,125,300,35);

		//button
		book = new JButton ("Book Ticket");
		book.setFont (f1);
		book.setBounds (475,200,300,50);
		book.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					bookAction (ae);
				}
			});

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (accNoL);
		mainPanel.add (bankNameL);
		mainPanel.add (priceL);
		mainPanel.add (accNoTf);
		mainPanel.add (bankNameTf);
		mainPanel.add (book);
	}

	//actionlistener for the book button
	public void bookAction (ActionEvent ae)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement ("insert into payment_details values (?,?,?,?)");
			ps.setString (1,bookingCode);
			ps.setLong (2, Long.parseLong (accNoTf.getText ()));
			ps.setString (3,bankNameTf.getText ());
			ps.setDouble (4,(jPrice+rPrice));
			int status = ps.executeUpdate ();
			if (status != 0)
			{
				ps = conn.prepareStatement ("update flight_booking_details set remaining_seats = remaining_seats-1 where flight_code = ?");
				ps.setString (1,jFlight);
				ps.executeUpdate ();
				ps = conn.prepareStatement ("update flight_booking_details set remaining_seats = remaining_seats-1 where flight_code = ?");
				ps.setString (1,rFlight);
				ps.executeUpdate ();
				dispose ();
				setVisible (false);
				Confirmation c = new Confirmation (bookingCode);
				c.setVisible (true);
				c.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
				c.setBounds (0,0,1250,700);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
}

class Confirmation extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static JLabel confirmLabel;
	static Font f1;
	static JButton goToHome;
	Confirmation (String bookingCode)
	{
		//fonts
		f1 = new Font ("Eras Medium ITC",Font.BOLD,25);

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//panel
		mainPanel = new JPanel ();
		mainPanel.setLayout (null);
		mainPanel.setBounds (0,0,1250,700);

		//labels
		confirmLabel = new JLabel ("Your booking number is - "+bookingCode);
		confirmLabel.setFont (f1);
		confirmLabel.setForeground (Color.BLUE);
		confirmLabel.setBounds (200,325,850,50);

		//button
		goToHome = new JButton ("Back To Home");
		goToHome.setFont (f1);
		goToHome.setBounds (475,200,300,50);
		goToHome.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					goToHomeAction (ae);
				}
			});

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (confirmLabel);
		mainPanel.add (goToHome);
	}

	public void goToHomeAction (ActionEvent ae)
	{
		dispose ();
		setVisible (false);
		MainBook mbFrame = new MainBook ();
		mbFrame.setVisible (true);
		mbFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mbFrame.setBounds (0,0,1250,700);
	}
}

class Cancellation extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static Font f1;
	static JLabel bookCodeL, dataL;
	static JTextField bookCodeTf;
	static JButton okBtn, confirmCancel, goToHome;
	static Connection conn;
	static String bookCodeStr = "";
	Cancellation ()
	{
		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//panel
		mainPanel = new JPanel ();
		mainPanel.setLayout (null);
		mainPanel.setBounds (0,0,1250,700);

		//labels
		bookCodeL = new JLabel ("Enter Booking Code");
		bookCodeL.setFont (f1);
		bookCodeL.setBounds (50,50,200,35);

		dataL = new JLabel ();
		dataL.setFont (f1);
		dataL.setBounds (50,100,1000,200);
		
		//textfields
		bookCodeTf = new JTextField ();
		bookCodeTf.setFont (f1);
		bookCodeTf.setBounds (275,50,150,35);
		
		//buttons
		okBtn = new JButton ("OK");
		okBtn.setFont (f1);
		okBtn.setBounds (450,50,100,35);
		okBtn.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					okBtnAction (ae);
				}
			});

		confirmCancel = new JButton ("Confirm");
		confirmCancel.setFont (f1);
		confirmCancel.setBounds (1050,100,200,50);
		confirmCancel.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					confirmCancelAction (ae);
				}
			});
		
		goToHome = new JButton ("Go To Home");
		goToHome.setFont (f1);
		goToHome.setBounds (525,400,300,50);
		goToHome.addActionListener (new ActionListener ()
			{
				public void actionPerformed (ActionEvent ae)
				{
					goToHomeAction (ae);
				}
			});

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (bookCodeL);
		mainPanel.add (okBtn);
		mainPanel.add (bookCodeTf);
		mainPanel.add (goToHome);

		try
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	//actionlisteners 
	public void okBtnAction (ActionEvent ae)
	{
		bookCodeStr = bookCodeTf.getText ();
		try
		{
			PreparedStatement ps = conn.prepareStatement ("select * from customer_booking_details where booking_code = ?");
			ps.setString (1,bookCodeStr);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
				dataL.setText ("<html>Name: "+rs.getString ("name")+"<br>Email Address: "+rs.getString ("email")+"<br>Phone: "+rs.getLong ("phone")+"<br>Age: "+rs.getInt ("age")+"<br><br>Confirm cancellation?</html>");
			else
				dataL.setText ("No history found!");
			mainPanel.add (dataL);
			mainPanel.add (okBtn);
			mainPanel.add (confirmCancel);
			mainPanel.revalidate ();
			mainPanel.repaint ();
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	public void confirmCancelAction (ActionEvent ae)
	{
		try
		{
			PreparedStatement ps = conn.prepareStatement ("insert into cancellation_details values (?)");
			ps.setString (1,bookCodeStr);
			ps.executeUpdate ();
			ps = conn.prepareStatement ("update flight_booking_details set remaining_seats = remaining_seats + 1 where flight_code in (select flight_code from customer_booking_details where booking_code = ?)");
			ps.setString (1,bookCodeStr);
			ps.executeUpdate ();
			dataL.setText ("Successful! Refundable within 7 days!");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	public void goToHomeAction (ActionEvent ae)
	{
		dispose ();
		setVisible (false);
		MainBook mbFrame = new MainBook ();
		mbFrame.setVisible (true);
		mbFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mbFrame.setBounds (0,0,1250,700);
	}
}

public class AirlineReservationSystem
{
	public static void main (String [] args)
	{
		MainBook mbFrame = new MainBook ();
		mbFrame.setVisible (true);
		mbFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mbFrame.setBounds (0,0,1250,700);
	}
}