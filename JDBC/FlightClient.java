import java.rmi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*; 
import java.util.*;
import java.rmi.registry.*;

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
	static FlightInterface stub;
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
			stub = (FlightInterface) Naming.lookup ("rmi://localhost:5000/test");
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
			String flightDet = stub.getFlights (src, dest);
			String[] flights = flightDet.split ("#");
			flightData1.setText ("");
			flightData2.setText ("");
			flightData1.setText ("\tFlight Code\tDeparture\tArrival\tPrice\tDuration\n");
			flightData2.setText ("\tFlight Code\tDeparture\tArrival\tPrice\tDuration\n");
			flightBox1.removeAllItems ();
			flightBox1.addItem ("--Select Flight--");
			flightBox2.removeAllItems ();
			flightBox2.addItem ("--Select Flight--");
			for (int i=1 ; i<=10 ; i++) {
				if (i <= 5)
				{
					String[] tokens = flights [i-1].split ("%");
					flightData1.append (tokens [0]+"\n");
					flightBox1.addItem (tokens [1]);
				}
				else
				{
					String[] tokens = flights [i-1].split ("%");
					flightData2.append (tokens [0]+"\n");
					flightBox2.addItem (tokens [1]);
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
				String jFlight = (String) flightBox1.getSelectedItem ();
				String rFlight = (String) flightBox2.getSelectedItem ();
				String seats = stub.getSeats (jDate,rDate,jFlight,rFlight);
				String[] seat = seats.split ("%");
				for (String token: seat)
					System.out.println (token);
				int jRemaining = Integer.parseInt (seat[0]);
				int rRemaining = Integer.parseInt (seat[1]);
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
				String jFlight = (String) flightBox1.getSelectedItem ();
				String seat = stub.getSeat (jDate,jFlight);
				int jRemaining = Integer.parseInt (seat);
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
	static FlightInterface stub;
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
			stub = (FlightInterface) Naming.lookup ("rmi://localhost:5000/test");
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
			stub = (FlightInterface) Naming.lookup ("rmi://localhost:5000/test");
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
			String str = stub.bookSeat (nameTf.getText (),emailTf.getText (),Long.parseLong(phoneTf.getText ()),Integer.parseInt (ageTf.getText ()),jFlight,rFlight);
			String[] nums = str.split ("%");
			int bookingCode = Integer.parseInt (nums [0]);
			int status = Integer.parseInt (nums [1]);
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
	static FlightInterface stub;
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

		int jPrice = 0, rPrice = 0;
		try 
		{
			stub = (FlightInterface) Naming.lookup ("rmi://localhost:5000/test");
			jPrice = stub.getJPrice (jFlight);
			if (!rFlight.equals (""))
			{
				rPrice = stub.getRPrice (rFlight);
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
			int status = stub.payAmt (bookingCode, Long.parseLong (accNoTf.getText ()), bankNameTf.getText (), (jPrice+rPrice));
			if (status != 0)
			{
				stub.setJSeat (jFlight);
				stub.setRSeat (rFlight);
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
	static FlightInterface stub;
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
			stub = (FlightInterface) Naming.lookup ("rmi://localhost:5000/test");
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
			String custDet = stub.getCustDet (bookCodeStr);
			dataL.setText (custDet);
			mainPanel.add (dataL);
			if (custDet.equals ("No history found!"))
				mainPanel.remove (confirmCancel);
			else
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
			stub.prepareCancellation (bookCodeStr);
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

public class FlightClient
{
	public static void main (String [] args)
	{
		MainBook mbFrame = new MainBook ();
		mbFrame.setVisible (true);
		mbFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mbFrame.setBounds (0,0,1250,700);
	}
}

/*import java.io.*;
import java.rmi.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*; 
import java.util.*;
import java.rmi.registry.*;

class MainBook extends JFrame implements ItemListener
{
	static Container container;
	static JPanel mainPanel;
	static JCheckBox zeroFive, fiveTwelve, twelveMore, returnJourney;
	static JComboBox sourceBox, destinationBox;
	static JButton search;
	static JTextField journeyField, returnField;
	static JLabel journeyLabel, returnLabel;
	static Font f1;
	static FlightInterface stub;
	MainBook ()
	{
		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);

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

		try 
		{
			stub = (FlightInterface) Naming.lookup ("rmi://localhost:5000/test");
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
		try
		{
			ArrayList al = stub.getFlights (src, dest);
			for (int i = 0; i<al.size(); i++)
			{
				FirstObject fo = (FirstObject) al.get (i);
				System.out.println (fo.srcCode);
				System.out.println (fo.flightCode);
			}
		}
		catch (Exception e)
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
			}/*
			else
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
}

public class FlightClient
{
	public static void main (String [] args)
	{
		MainBook mbFrame = new MainBook ();
		mbFrame.setVisible (true);
		mbFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		mbFrame.setBounds (0,0,1250,700);
	}
}*/