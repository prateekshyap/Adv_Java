import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

class StudentFrame extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static JButton registration, login;
	static JLabel usernameL, passwordL;
	static JTextField usernameTf;
	static JPasswordField passwordPf;
	static Font f1, f2;
	static Connection conn;
	StudentFrame ()
	{
		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);
		f2 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		mainPanel = new JPanel ();
		mainPanel.setBounds (0,0,1250,700);
		mainPanel.setLayout (null);

		//labels
		usernameL = new JLabel ("Username");
		usernameL.setBounds (400,200,200,35);
		usernameL.setFont (f1);

		passwordL = new JLabel ("Password");
		passwordL.setBounds (400,250,200,35);
		passwordL.setFont (f1);

		//textfield
		usernameTf = new JTextField ();
		usernameTf.setFont (f1);
		usernameTf.setBounds (600,200,200,35);

		//passwordfield
		passwordPf = new JPasswordField ();
		passwordPf.setEchoChar ('*');
		passwordPf.setBounds (600,250,200,35);
		passwordPf.setFont (f1);

		//buttons
		registration = new JButton ("New User? Register here.");
		registration.setFont (f1);
		registration.setBounds (400,380,400,50);
		registration.setOpaque (false);
		registration.setContentAreaFilled (false);
		registration.setBorder (BorderFactory.createEmptyBorder ());
		registration.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				registrationAction (ae);
			}
		});

		login = new JButton ("Login");
		login.setFont (f2);
		login.setBounds (400,325,400,50);
		login.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				loginAction (ae);
			}
		});

		//adding the components to the panel
		mainPanel.add (usernameL);
		mainPanel.add (passwordL);
		mainPanel.add (usernameTf);
		mainPanel.add (passwordPf);
		mainPanel.add (registration);
		mainPanel.add (login);

		//adding the main panel to the container
		container.add (mainPanel);

		//connection to database
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

	public void loginAction (ActionEvent ae)
	{
		try
		{
			Statement s = conn.createStatement ();
			ResultSet rs = s.executeQuery ("select user_id, password, name from StudentSystem");
			while (rs.next ())
			{
				if (usernameTf.getText ().equals (rs.getString (1))&&passwordPf.getText ().equals (rs.getString (2)))
				{
					setVisible (false);
					dispose ();
					Profile p = new Profile (rs.getString ("name"), rs.getString ("user_id"), rs.getString ("password"));
					p.setVisible (true);
					p.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
					p.setBounds (0,0,1250,700);
					break;
				}
				else
				{
					System.out.println (rs.getString (1) + " " + rs.getString (2));
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	public static void registrationAction (ActionEvent ae)
	{
		SignUpFrame suf = new SignUpFrame ();
		suf.setVisible (true);
		suf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		suf.setBounds (0,0,1250,700);
	}
}

class SignUpFrame extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static JLabel nameL, fatherL, motherL, rollNoL, regdNoL, streamL, branchL, semL, phoneL, emailL, genderL, hobbiesL, dobL, addrL, warningL;
	static JTextField nameTf, fatherTf, motherTf, rollNoTf, regdNoTf, phoneTf, emailTf, dobTf;
	static JTextArea addrTa;
	static JComboBox streamCb, branchCb, semCb, hobbiesCb;
	static JRadioButton maleRb, femaleRb, otherRb;
	static JButton register;
	static Font f1;
	static Connection conn;
	SignUpFrame ()
	{
		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		//buttongroups
		ButtonGroup genderGroup = new ButtonGroup ();

		mainPanel = new JPanel ();
		mainPanel.setBounds (0,0,1250,700);
		mainPanel.setLayout (null);

		//labels
		nameL = new JLabel ("Name");
		nameL.setBounds (50+100,10,75+50,35);
		nameL.setFont (f1);

		fatherL = new JLabel ("Father's Name");
		fatherL.setBounds (50+100,60,75+50,35);
		fatherL.setFont (f1);

		motherL = new JLabel ("Mother's Name");
		motherL.setBounds (50+100,110,75+50,35);
		motherL.setFont (f1);

		rollNoL = new JLabel ("Roll No");
		rollNoL.setBounds (50+100,160,75+50,35);
		rollNoL.setFont (f1);

		regdNoL = new JLabel ("Regd No");
		regdNoL.setBounds (50+100,210,75+50,35);
		regdNoL.setFont (f1);

		streamL = new JLabel ("Stream");
		streamL.setBounds (50+100,260,75+50,35);
		streamL.setFont (f1);

		branchL = new JLabel ("Branch");
		branchL.setBounds (50+100,310,75+50,35);
		branchL.setFont (f1);

		semL = new JLabel ("Semester");
		semL.setBounds (50+100,360,75+50,35);
		semL.setFont (f1);

		phoneL = new JLabel ("Phone");
		phoneL.setBounds (50+100,410,75+50,35);
		phoneL.setFont (f1);

		emailL = new JLabel ("Email Address");
		emailL.setBounds (750-100,10,75+50,35);
		emailL.setFont (f1);

		genderL = new JLabel ("Gender");
		genderL.setBounds (750-100,60,75+50,35);
		genderL.setFont (f1);

		hobbiesL = new JLabel ("Hobbies");
		hobbiesL.setBounds (750-100,110,75+50,35);
		hobbiesL.setFont (f1);

		dobL = new JLabel ("DOB (dd/mm/yyyy)");
		dobL.setBounds (750-100,160,75+50+25,35);
		dobL.setFont (f1);

		addrL = new JLabel ("Address");
		addrL.setBounds (750-100,210,75+50,35);
		addrL.setFont (f1);

		warningL = new JLabel ();
		warningL.setBounds (350+100-100,575,500,35);
		warningL.setFont (f1);
		warningL.setForeground (Color.RED);

		//textfields
		nameTf = new JTextField ();
		nameTf.setBounds (150+50+100,10,200,35);
		nameTf.setFont (f1);

		fatherTf = new JTextField ();
		fatherTf.setBounds (150+50+100,60,200,35);
		fatherTf.setFont (f1);
		
		motherTf = new JTextField ();
		motherTf.setBounds (150+50+100,110,200,35);
		motherTf.setFont (f1);
		
		rollNoTf = new JTextField ();
		rollNoTf.setBounds (150+50+100,160,200,35);
		rollNoTf.setFont (f1);
		
		regdNoTf = new JTextField ();
		regdNoTf.setBounds (150+50+100,210,200,35);
		regdNoTf.setFont (f1);
		
		phoneTf = new JTextField ();
		phoneTf.setBounds (150+50+100,410,200,35);
		phoneTf.setFont (f1);
		
		emailTf = new JTextField ();
		emailTf.setBounds (150+750+50-100,10,200,35);
		emailTf.setFont (f1);
		
		dobTf = new JTextField ();
		dobTf.setBounds (150+750+50-100,160,200,35);
		dobTf.setFont (f1);

		//textareas
		addrTa = new JTextArea ();
		addrTa.setBounds (750+150+50-100,210,200,200);
		addrTa.setFont (f1);

		//comboboxes
		streamCb = new JComboBox ();
		streamCb.addItem ("--Select Stream--");
		streamCb.addItem ("BTech");
		streamCb.addItem ("MTech");
		streamCb.addItem ("Diploma");
		streamCb.addItem ("MSc");
		streamCb.addItem ("MCA");
		streamCb.addItem ("BArch");
		streamCb.setBounds (50+150+100,260,200,35);
		streamCb.setFont (f1);

		branchCb = new JComboBox ();
		branchCb.addItem ("--Select Branch--");
		branchCb.addItem("Architecture");
		branchCb.addItem("Chemical");
		branchCb.addItem("Civil");
		branchCb.addItem("CSE");
		branchCb.addItem("CSE (SSC)");
		branchCb.addItem("CS & IS");
		branchCb.addItem("Electrical");
		branchCb.addItem("E & TC");
		branchCb.addItem("E & TC (SSC)");
		branchCb.addItem("EC & M");
		branchCb.addItem("Energy System");
		branchCb.addItem("Geotechnical");
		branchCb.addItem("IM");
		branchCb.addItem("MCA");
		branchCb.addItem("Mechanical");
		branchCb.addItem("MSD");
		branchCb.addItem("MME");
		branchCb.addItem("Metallurgical");
		branchCb.addItem("PED");
		branchCb.addItem("PSE");
		branchCb.addItem("Production");
		branchCb.addItem("Structural");
		branchCb.addItem("Thermal");
		branchCb.addItem("Transportation");
		branchCb.addItem("WCT");
		branchCb.setBounds (50+150+100,310,200,35);
		branchCb.setFont (f1);

		semCb = new JComboBox ();
		semCb.addItem ("--Select Semester--");
		semCb.addItem ("1st");
		semCb.addItem ("2nd");
		semCb.addItem ("3rd");
		semCb.addItem ("4th");
		semCb.addItem ("5th");
		semCb.addItem ("6th");
		semCb.addItem ("7th");
		semCb.addItem ("8th");
		semCb.addItem ("9th");
		semCb.addItem ("10th");
		semCb.setBounds (50+150+100,360,200,35);
		semCb.setFont (f1);

		hobbiesCb = new JComboBox ();
		hobbiesCb.addItem ("--Select Hobby--");
		hobbiesCb.addItem ("Singing");
		hobbiesCb.addItem ("Dancing");
		hobbiesCb.addItem ("Reading Novels");
		hobbiesCb.addItem ("Writing");
		hobbiesCb.addItem ("Sports");
		hobbiesCb.addItem ("Other");
		//hobbiesCb.addItem ("");
		//hobbiesCb.addItem ("");
		hobbiesCb.setBounds (750+150+50-100,110,200,35);
		hobbiesCb.setFont (f1);

		//radiobuttons
		maleRb = new JRadioButton ("Male");
		maleRb.setBounds (750+150+50-100,60,58,35);
		maleRb.setFont (f1);

		femaleRb = new JRadioButton ("Female");
		femaleRb.setBounds (750+150+50+63-100,60,73,35);
		femaleRb.setFont (f1);
		
		otherRb = new JRadioButton ("Other");
		otherRb.setBounds (750+150+50+63+78-100,60,65,35);
		otherRb.setFont (f1);

		//buttons
		register = new JButton ("Register");
		register.setFont (f1);
		register.setBounds (500,500,200,50);
		register.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				registerAction (ae);
			}
		});

		//adding panel to container
		container.add (mainPanel);

		//adding components to the panel
		mainPanel.add (nameL);
		mainPanel.add (fatherL);
		mainPanel.add (motherL);
		mainPanel.add (rollNoL);
		mainPanel.add (regdNoL);
		mainPanel.add (streamL);
		mainPanel.add (branchL);
		mainPanel.add (semL);
		mainPanel.add (phoneL);
		mainPanel.add (emailL);
		mainPanel.add (genderL);
		mainPanel.add (hobbiesL);
		mainPanel.add (dobL);
		mainPanel.add (addrL);
		mainPanel.add (nameTf);
		mainPanel.add (fatherTf);
		mainPanel.add (rollNoTf);
		mainPanel.add (motherTf);
		mainPanel.add (regdNoTf);
		mainPanel.add (phoneTf);
		mainPanel.add (dobTf);
		mainPanel.add (emailTf);
		mainPanel.add (addrTa);
		mainPanel.add (streamCb);
		mainPanel.add (branchCb);
		mainPanel.add (semCb);
		mainPanel.add (hobbiesCb);
		mainPanel.add (maleRb);
		mainPanel.add (femaleRb);
		mainPanel.add (otherRb);
		mainPanel.add (register);
		mainPanel.add (warningL);

		//adding the radiobuttons to the buttongroup
		genderGroup.add (maleRb);
		genderGroup.add (femaleRb);
		genderGroup.add (otherRb);

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

	public void registerAction (ActionEvent ae)
	{
		String nameStr = nameTf.getText ();
		String fatherStr = fatherTf.getText ();
		String motherStr = motherTf.getText ();
		String rollStr = rollNoTf.getText ();
		String regdStr = regdNoTf.getText ();
		String streamStr = (String) streamCb.getSelectedItem ();
		String branchStr = (String) branchCb.getSelectedItem ();
		String semStr = (String) semCb.getSelectedItem ();
		String hobbyStr = (String) hobbiesCb.getSelectedItem ();
		String addrStr = addrTa.getText ();
		String genderStr = "";
		if (maleRb.isSelected ())
			genderStr = "M";
		else if (femaleRb.isSelected ())
			genderStr = "F";
		else if (otherRb.isSelected ())
			genderStr = "O";
		else
			warningL.setText ("Please Select Gender!");
		long rollNum=0, regdNum=0;
		try
		{
			rollNum = Long.parseLong (rollStr);
		}
		catch (Exception e)
		{
			warningL.setText ("Please enter valid Roll No!");
		}
		try
		{
			regdNum = Long.parseLong (regdStr);
		}
		catch (Exception e)
		{
			warningL.setText ("Please enter valid Regd No!");
		}
		String phoneStr = phoneTf.getText ();
		boolean phStr=true;
		long pho=0;
		try
		{
			pho=Long.parseLong(phoneStr);
		}catch(Exception ee)
		{
			warningL.setText ("Please Enter a valid Phone number!");
		}
		String ph=Long.toString(pho);
		if(ph.length()>10||ph.length()<10)
			warningL.setText ("Please Enter a valid Phone Number!");
		else
		{
			for(int i=0;i<ph.length();i++)
			{
				char ch=ph.charAt(i);
				if(ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9')
					{}
				else
					warningL.setText ("Please Enter a valid Phone Number!");
			}
		}
		String emailStr = emailTf.getText ();
		int flag=0;
		boolean eStr=false;
		for(int i=0;i<emailStr.length();i++)
		{
			char ch=emailStr.charAt(i);
			if(ch=='@')
			{
				flag++;
				break;
			}
		}
		if(flag==1)
			eStr=true;
		else
			eStr=false;
		String dobStr = dobTf.getText ();
		if(dobStr.length()>10||dobStr.length()<10)
			warningL.setText ("Please enter in dd/mm/yyyy format!");
		else
		{
			String y=dobStr.substring(6,10);
			int iy=Integer.parseInt(y);
			String m=dobStr.substring(3,5);
			String dt=dobStr.substring(0,2);
			int i=iy%4;
			if(i==0)
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30")))
					warningL.setText ("Please enter a valid DOB!");
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					warningL.setText ("Please enter a valid DOB!");
				else
				{
					if (nameStr.equals ("")||fatherStr.equals ("")||motherStr.equals ("")||rollStr.equals ("")||regdStr.equals ("")||streamStr.equals ("--Select Stream--")||branchStr.equals ("--Select Branch--")||semStr.equals ("--Select Semester--")||hobbyStr.equals ("--Select Hobby--")||addrStr.equals ("")||genderStr.equals (""))
						warningL.setText ("Incomplete Information!");
					else
					{
						try
						{
							PreparedStatement ps = conn.prepareStatement ("insert into StudentSystem values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							ps.setLong (1,regdNum);
							ps.setLong (2,rollNum);
							ps.setString (3,nameStr);
							ps.setString (4,fatherStr);
							ps.setString (5,motherStr);
							ps.setString (6,streamStr);
							ps.setString (7,branchStr);
							ps.setString (8,semStr);
							ps.setLong (9,pho);
							ps.setString (10,emailStr);
							ps.setString (11,genderStr);
							ps.setString (12,hobbyStr);
							SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
							java.util.Date ud=sdf.parse(dobStr);
							java.sql.Date sqd=new java.sql.Date(ud.getTime());
							ps.setDate (13,sqd);
							ps.setString (14,addrStr);
							ps.setString (15,Long.toString (regdNum));
							ps.setString (16,Long.toString (rollNum));
							int status = ps.executeUpdate ();
							if (status != 0)
							{
								setVisible (false);
								dispose ();
							}
							else
								warningL.setText ("Error!");
						}
						catch (Exception e)
						{
							e.printStackTrace ();
						}
					}
				}
			}
			else
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30"))||(m.equals("02")&&dt.equals("29")))
					warningL.setText ("Please enter a valid DOB!");
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					warningL.setText ("Please enter a valid DOB!");
				else
				{
					if (nameStr.equals ("")||fatherStr.equals ("")||motherStr.equals ("")||rollStr.equals ("")||regdStr.equals ("")||streamStr.equals ("--Select Stream--")||branchStr.equals ("--Select Branch--")||semStr.equals ("--Select Semester--")||hobbyStr.equals ("--Select Hobby--")||addrStr.equals ("")||genderStr.equals (""))
						warningL.setText ("Incomplete Information!");
					else
					{
						try
						{
							PreparedStatement ps = conn.prepareStatement ("insert into StudentSystem values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							ps.setLong (1,regdNum);
							ps.setLong (2,rollNum);
							ps.setString (3,nameStr);
							ps.setString (4,fatherStr);
							ps.setString (5,motherStr);
							ps.setString (6,streamStr);
							ps.setString (7,branchStr);
							ps.setString (8,semStr);
							ps.setLong (9,pho);
							ps.setString (10,emailStr);
							ps.setString (11,genderStr);
							ps.setString (12,hobbyStr);
							SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
							java.util.Date ud=sdf.parse(dobStr);
							java.sql.Date sqd=new java.sql.Date(ud.getTime());
							ps.setDate (13,sqd);
							ps.setString (14,addrStr);
							ps.setString (15,Long.toString (regdNum));
							ps.setString (16,Long.toString (rollNum));
							int status = ps.executeUpdate ();
							if (status != 0)
							{
								setVisible (false);
								dispose ();
							}
							else
								warningL.setText ("Error!");
						}
						catch (Exception e)
						{
							e.printStackTrace ();
						}
					}
				}
			}
		}
	}
}

class Profile extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static JLabel head;
	static Connection conn;
	static Font f1, f2;
	static JButton logout, changePw, editProfile, search;
	static String userid = "", password = "";
	Profile (String name, String userid, String password)
	{
		this.userid = userid;
		this.password = password;
		try
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		
		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);
		f2 = new Font ("Lucida Calligraphy",Font.BOLD,30);

		mainPanel = new JPanel ();
		mainPanel.setBounds (0,0,1250,700);
		mainPanel.setLayout (null);

		//head welcome label
		head = new JLabel ("Welcome "+name+"!");
		head.setBounds (300,5,700,50);
		head.setFont (f2);

		//buttons
		logout = new JButton ("Logout");
		logout.setFont (f1);
		logout.setBounds (700,300,200,50);
		logout.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				logoutAction (ae);
			}
		});

		changePw = new JButton ("Change Password");
		changePw.setFont (f1);
		changePw.setBounds (400,300,200,50);
		changePw.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				changePwAction (ae);
			}
		});

		editProfile = new JButton ("Edit Profile");
		editProfile.setFont (f1);
		editProfile.setBounds (700,200,200,50);
		editProfile.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				editProfileAction (ae);
			}
		});

		search = new JButton ("Search");
		search.setFont (f1);
		search.setBounds (400,200,200,50);
		search.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				searchAction (ae);
			}
		});

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (head);
		mainPanel.add (logout);
		mainPanel.add (search);
		mainPanel.add (changePw);
		mainPanel.add (editProfile);
	}

	public void logoutAction (ActionEvent ae)
	{
		setVisible (false);
		dispose ();
		StudentFrame sFrame = new StudentFrame ();
		sFrame.setVisible (true);
		sFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		sFrame.setBounds (0,0,1250,700);
	}

	public void changePwAction (ActionEvent ae)
	{
		ChangePasswordFrame cpf = new ChangePasswordFrame(userid, password);
		cpf.setVisible (true);
		cpf.setBounds (0,0,1250,700);
		cpf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}

	public void editProfileAction (ActionEvent ae)
	{
		EditProfileFrame epf = new EditProfileFrame(userid);
		epf.setVisible (true);
		epf.setBounds (0,0,1250,700);
		epf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}

	public void searchAction (ActionEvent ae)
	{
		SearchFrame sf = new SearchFrame();
		sf.setVisible (true);
		sf.setBounds (0,0,1250,700);
		sf.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}
}

class ChangePasswordFrame extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static JPasswordField oldPass, newPass, rePass;
	static JToggleButton viewPass;
	static JLabel oldL, newL, reL, warningL;
	static JButton submit, reset;
	static Connection conn;
	static Font f1;
	static String userid = "", password = "";
	ChangePasswordFrame (String userid, String password)
	{
		this.userid = userid;
		this.password = password;
		//container
		container = this.getContentPane ();
		container.setLayout (null);

		mainPanel = new JPanel ();
		mainPanel.setBounds (0,0,1250,700);
		mainPanel.setLayout (null);

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,20);

		//passwordfields
		oldPass = new JPasswordField ();
		oldPass.setBounds (500+100,200,200,35);
		oldPass.setFont (f1);
		oldPass.setEchoChar ('*');

		newPass = new JPasswordField ();
		newPass.setBounds (500+100,250,200,35);
		newPass.setFont (f1);
		newPass.setEchoChar ('*');

		rePass = new JPasswordField ();
		rePass.setBounds (500+100,300,200,35);
		rePass.setFont (f1);
		rePass.setEchoChar ('*');

		//togglebuttons
		viewPass = new JToggleButton ("View Password");
		viewPass.setBounds (200,350,200,50);
		viewPass.setFont (f1);
		viewPass.addItemListener (new ItemListener()
		{
			public void itemStateChanged (ItemEvent ie)
			{
				viewPassStateChanged (ie);
			}
		});

		//labels
		oldL = new JLabel ("Old Password");
		oldL.setBounds (250+100,200,200,35);
		oldL.setFont (f1);

		newL = new JLabel ("New Password");
		newL.setBounds (250+100,250,200,35);
		newL.setFont (f1);

		reL = new JLabel ("Retype Password");
		reL.setBounds (250+100,300,200,35);
		reL.setFont (f1);

		warningL = new JLabel ();
		warningL.setBounds (250+100,425,500,50);
		warningL.setFont (f1);

		//buttons
		submit = new JButton ("Submit");
		submit.setBounds (450,350,200,50);
		submit.setFont (f1);
		submit.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				submitAction (ae);
			}
		});

		reset = new JButton ("Reset");
		reset.setBounds (700,350,200,50);
		reset.setFont (f1);
		reset.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				resetAction (ae);
			}
		});

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (oldPass);
		mainPanel.add (newPass);
		mainPanel.add (rePass);
		mainPanel.add (viewPass);
		mainPanel.add (oldL);
		mainPanel.add (newL);
		mainPanel.add (reL);
		mainPanel.add (submit);
		mainPanel.add (reset);
		mainPanel.add (warningL);

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

	public void viewPassStateChanged (ItemEvent ie)
	{
		if (viewPass.isSelected ())
		{
			oldPass.setEchoChar ((char)0);
			newPass.setEchoChar ((char)0);
			rePass.setEchoChar ((char)0);
		}
		else
		{
			oldPass.setEchoChar ('*');
			newPass.setEchoChar ('*');
			rePass.setEchoChar ('*');
		}
	}

	public void submitAction (ActionEvent ae)
	{
		try
		{
			if (!oldPass.getText ().equals (password))
				warningL.setText ("Incorrect old password!");
			if (!(newPass.getText ().equals (rePass.getText ())))
				warningL.setText ("Password did not match!");
			else
			{
				PreparedStatement ps = conn.prepareStatement ("update StudentSystem set password=? where user_id=?");
				ps.setString (1,newPass.getText ());
				ps.setString (2,userid);
				int status = ps.executeUpdate ();
				if (status != 0)
				{
					warningL.setText ("Password changed successfully!");
					Thread.sleep (1500);
				}
				else
					warningL.setText ("Error!");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
		setVisible (false);
		dispose ();
	}

	public void resetAction (ActionEvent ae)
	{
		oldPass.setText ("");
		newPass.setText ("");
		rePass.setText ("");
	}
}

class EditProfileFrame extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static JLabel nameL, fatherL, motherL, rollNoL, regdNoL, streamL, branchL, semL, phoneL, emailL, genderL, hobbiesL, dobL, addrL, warningL;
	static JTextField nameTf, fatherTf, motherTf, rollNoTf, regdNoTf, phoneTf, emailTf, dobTf;
	static JTextArea addrTa;
	static JComboBox streamCb, branchCb, semCb, hobbiesCb;
	static JRadioButton maleRb, femaleRb, otherRb;
	static JButton register;
	static Font f1;
	static Connection conn;
	static String userid;
	EditProfileFrame (String userid)
	{
		this.userid = userid;

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		mainPanel = new JPanel ();
		mainPanel.setBounds (0,0,1250,700);
		mainPanel.setLayout (null);

		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		//buttongroups
		ButtonGroup genderGroup = new ButtonGroup ();

		//labels
		nameL = new JLabel ("Name");
		nameL.setBounds (50+100,10,75+50,35);
		nameL.setFont (f1);

		fatherL = new JLabel ("Father's Name");
		fatherL.setBounds (50+100,60,75+50,35);
		fatherL.setFont (f1);

		motherL = new JLabel ("Mother's Name");
		motherL.setBounds (50+100,110,75+50,35);
		motherL.setFont (f1);

		rollNoL = new JLabel ("Roll No");
		rollNoL.setBounds (50+100,160,75+50,35);
		rollNoL.setFont (f1);

		regdNoL = new JLabel ("Regd No");
		regdNoL.setBounds (50+100,210,75+50,35);
		regdNoL.setFont (f1);

		streamL = new JLabel ("Stream");
		streamL.setBounds (50+100,260,75+50,35);
		streamL.setFont (f1);

		branchL = new JLabel ("Branch");
		branchL.setBounds (50+100,310,75+50,35);
		branchL.setFont (f1);

		semL = new JLabel ("Semester");
		semL.setBounds (50+100,360,75+50,35);
		semL.setFont (f1);

		phoneL = new JLabel ("Phone");
		phoneL.setBounds (50+100,410,75+50,35);
		phoneL.setFont (f1);

		emailL = new JLabel ("Email Address");
		emailL.setBounds (750-100,10,75+50,35);
		emailL.setFont (f1);

		genderL = new JLabel ("Gender");
		genderL.setBounds (750-100,60,75+50,35);
		genderL.setFont (f1);

		hobbiesL = new JLabel ("Hobbies");
		hobbiesL.setBounds (750-100,110,75+50,35);
		hobbiesL.setFont (f1);

		dobL = new JLabel ("DOB (dd/mm/yyyy)");
		dobL.setBounds (750-100,160,75+50+25,35);
		dobL.setFont (f1);

		addrL = new JLabel ("Address");
		addrL.setBounds (750-100,210,75+50,35);
		addrL.setFont (f1);

		warningL = new JLabel ();
		warningL.setBounds (350+100-100,575,500,35);
		warningL.setFont (f1);
		warningL.setForeground (Color.RED);

		//textfields
		nameTf = new JTextField ();
		nameTf.setBounds (150+50+100,10,200,35);
		nameTf.setFont (f1);

		fatherTf = new JTextField ();
		fatherTf.setBounds (150+50+100,60,200,35);
		fatherTf.setFont (f1);
		
		motherTf = new JTextField ();
		motherTf.setBounds (150+50+100,110,200,35);
		motherTf.setFont (f1);
		
		rollNoTf = new JTextField ();
		rollNoTf.setBounds (150+50+100,160,200,35);
		rollNoTf.setFont (f1);
		rollNoTf.setEditable (false);
		
		regdNoTf = new JTextField ();
		regdNoTf.setBounds (150+50+100,210,200,35);
		regdNoTf.setFont (f1);
		regdNoTf.setEditable (false);
		
		phoneTf = new JTextField ();
		phoneTf.setBounds (150+50+100,410,200,35);
		phoneTf.setFont (f1);
		
		emailTf = new JTextField ();
		emailTf.setBounds (150+750+50-100,10,200,35);
		emailTf.setFont (f1);
		
		dobTf = new JTextField ();
		dobTf.setBounds (150+750+50-100,160,200,35);
		dobTf.setFont (f1);

		//textareas
		addrTa = new JTextArea ();
		addrTa.setBounds (750+150+50-100,210,200,200);
		addrTa.setFont (f1);

		//comboboxes
		streamCb = new JComboBox ();
		streamCb.addItem ("--Select Stream--");
		streamCb.addItem ("BTech");
		streamCb.addItem ("MTech");
		streamCb.addItem ("Diploma");
		streamCb.addItem ("MSc");
		streamCb.addItem ("MCA");
		streamCb.addItem ("BArch");
		streamCb.setBounds (50+150+100,260,200,35);
		streamCb.setFont (f1);

		branchCb = new JComboBox ();
		branchCb.addItem ("--Select Branch--");
		branchCb.addItem("Architecture");
		branchCb.addItem("Chemical");
		branchCb.addItem("Civil");
		branchCb.addItem("CSE");
		branchCb.addItem("CSE (SSC)");
		branchCb.addItem("CS & IS");
		branchCb.addItem("Electrical");
		branchCb.addItem("E & TC");
		branchCb.addItem("E & TC (SSC)");
		branchCb.addItem("EC & M");
		branchCb.addItem("Energy System");
		branchCb.addItem("Geotechnical");
		branchCb.addItem("IM");
		branchCb.addItem("MCA");
		branchCb.addItem("Mechanical");
		branchCb.addItem("MSD");
		branchCb.addItem("MME");
		branchCb.addItem("Metallurgical");
		branchCb.addItem("PED");
		branchCb.addItem("PSE");
		branchCb.addItem("Production");
		branchCb.addItem("Structural");
		branchCb.addItem("Thermal");
		branchCb.addItem("Transportation");
		branchCb.addItem("WCT");
		branchCb.setBounds (50+150+100,310,200,35);
		branchCb.setFont (f1);

		semCb = new JComboBox ();
		semCb.addItem ("--Select Semester--");
		semCb.addItem ("1st");
		semCb.addItem ("2nd");
		semCb.addItem ("3rd");
		semCb.addItem ("4th");
		semCb.addItem ("5th");
		semCb.addItem ("6th");
		semCb.addItem ("7th");
		semCb.addItem ("8th");
		semCb.addItem ("9th");
		semCb.addItem ("10th");
		semCb.setBounds (50+150+100,360,200,35);
		semCb.setFont (f1);

		hobbiesCb = new JComboBox ();
		hobbiesCb.addItem ("--Select Hobby--");
		hobbiesCb.addItem ("Singing");
		hobbiesCb.addItem ("Dancing");
		hobbiesCb.addItem ("Reading Novels");
		hobbiesCb.addItem ("Writing");
		hobbiesCb.addItem ("Sports");
		hobbiesCb.addItem ("Other");
		//hobbiesCb.addItem ("");
		//hobbiesCb.addItem ("");
		hobbiesCb.setBounds (750+150+50-100,110,200,35);
		hobbiesCb.setFont (f1);

		//radiobuttons
		maleRb = new JRadioButton ("Male");
		maleRb.setBounds (750+150+50-100,60,58,35);
		maleRb.setFont (f1);

		femaleRb = new JRadioButton ("Female");
		femaleRb.setBounds (750+150+50+63-100,60,73,35);
		femaleRb.setFont (f1);
		
		otherRb = new JRadioButton ("Other");
		otherRb.setBounds (750+150+50+63+78-100,60,65,35);
		otherRb.setFont (f1);

		//buttons
		register = new JButton ("Register");
		register.setFont (f1);
		register.setBounds (500,500,200,50);
		register.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				registerAction (ae);
			}
		});

		//adding panel to container
		container.add (mainPanel);

		//adding components to the panel
		mainPanel.add (nameL);
		mainPanel.add (fatherL);
		mainPanel.add (motherL);
		mainPanel.add (rollNoL);
		mainPanel.add (regdNoL);
		mainPanel.add (streamL);
		mainPanel.add (branchL);
		mainPanel.add (semL);
		mainPanel.add (phoneL);
		mainPanel.add (emailL);
		mainPanel.add (genderL);
		mainPanel.add (hobbiesL);
		mainPanel.add (dobL);
		mainPanel.add (addrL);
		mainPanel.add (nameTf);
		mainPanel.add (fatherTf);
		mainPanel.add (rollNoTf);
		mainPanel.add (motherTf);
		mainPanel.add (regdNoTf);
		mainPanel.add (phoneTf);
		mainPanel.add (dobTf);
		mainPanel.add (emailTf);
		mainPanel.add (addrTa);
		mainPanel.add (streamCb);
		mainPanel.add (branchCb);
		mainPanel.add (semCb);
		mainPanel.add (hobbiesCb);
		mainPanel.add (maleRb);
		mainPanel.add (femaleRb);
		mainPanel.add (otherRb);
		mainPanel.add (register);
		mainPanel.add (warningL);

		//adding the radiobuttons to the buttongroup
		genderGroup.add (maleRb);
		genderGroup.add (femaleRb);
		genderGroup.add (otherRb);

		try
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection ("jdbc:oracle:thin:@prateekshya:1522:orcl","prateekshya","Soni19994$");

			PreparedStatement ps = conn.prepareStatement ("select * from StudentSystem where user_id=?");
			ps.setString (1,userid);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
			{
				nameTf.setText (rs.getString ("name"));
				fatherTf.setText (rs.getString ("father"));
				motherTf.setText (rs.getString ("mother"));
				rollNoTf.setText (Long.toString (rs.getLong ("rollno")));
				regdNoTf.setText (Long.toString (rs.getLong ("regdno")));
				streamCb.setSelectedItem (rs.getString ("stream"));
				branchCb.setSelectedItem (rs.getString ("branch"));
				semCb.setSelectedItem (rs.getString ("semester"));
				phoneTf.setText (Long.toString (rs.getLong ("phone")));
				emailTf.setText (rs.getString ("email"));
				if (rs.getString ("gender").equals ("M"))
					maleRb.setSelected (true);
				else if (rs.getString ("gender").equals ("F"))
					femaleRb.setSelected (true);
				else if (rs.getString ("gender").equals ("O"))
					otherRb.setSelected (true);
				hobbiesCb.setSelectedItem (rs.getString ("hobby"));
				addrTa.setText (rs.getString ("address"));
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				java.sql.Date sqd = rs.getDate ("dob");
				java.util.Date ud = (java.util.Date)sqd;
				String d = sdf.format (ud);
				dobTf.setText (d);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}

	public void registerAction (ActionEvent ae)
	{
		String nameStr = nameTf.getText ();
		String fatherStr = fatherTf.getText ();
		String motherStr = motherTf.getText ();
		String rollStr = rollNoTf.getText ();
		String regdStr = regdNoTf.getText ();
		String streamStr = (String) streamCb.getSelectedItem ();
		String branchStr = (String) branchCb.getSelectedItem ();
		String semStr = (String) semCb.getSelectedItem ();
		String hobbyStr = (String) hobbiesCb.getSelectedItem ();
		String addrStr = addrTa.getText ();
		String genderStr = "";
		if (maleRb.isSelected ())
			genderStr = "M";
		else if (femaleRb.isSelected ())
			genderStr = "F";
		else if (otherRb.isSelected ())
			genderStr = "O";
		else
			warningL.setText ("Please Select Gender!");
		long rollNum=0, regdNum=0;
		try
		{
			rollNum = Long.parseLong (rollStr);
		}
		catch (Exception e)
		{
			warningL.setText ("Please enter valid Roll No!");
		}
		try
		{
			regdNum = Long.parseLong (regdStr);
		}
		catch (Exception e)
		{
			warningL.setText ("Please enter valid Regd No!");
		}
		String phoneStr = phoneTf.getText ();
		boolean phStr=true;
		long pho=0;
		try
		{
			pho=Long.parseLong(phoneStr);
		}catch(Exception ee)
		{
			warningL.setText ("Please Enter a valid Phone number!");
		}
		String ph=Long.toString(pho);
		if(ph.length()>10||ph.length()<10)
			warningL.setText ("Please Enter a valid Phone Number!");
		else
		{
			for(int i=0;i<ph.length();i++)
			{
				char ch=ph.charAt(i);
				if(ch=='0'||ch=='1'||ch=='2'||ch=='3'||ch=='4'||ch=='5'||ch=='6'||ch=='7'||ch=='8'||ch=='9')
					{}
				else
					warningL.setText ("Please Enter a valid Phone Number!");
			}
		}
		String emailStr = emailTf.getText ();
		int flag=0;
		boolean eStr=false;
		for(int i=0;i<emailStr.length();i++)
		{
			char ch=emailStr.charAt(i);
			if(ch=='@')
			{
				flag++;
				break;
			}
		}
		if(flag==1)
			eStr=true;
		else
			eStr=false;
		String dobStr = dobTf.getText ();
		if(dobStr.length()>10||dobStr.length()<10)
			warningL.setText ("Please enter in dd/mm/yyyy format!");
		else
		{
			String y=dobStr.substring(6,10);
			int iy=Integer.parseInt(y);
			String m=dobStr.substring(3,5);
			String dt=dobStr.substring(0,2);
			int i=iy%4;
			if(i==0)
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30")))
					warningL.setText ("Please enter a valid DOB!");
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					warningL.setText ("Please enter a valid DOB!");
				else
				{
					if (nameStr.equals ("")||fatherStr.equals ("")||motherStr.equals ("")||rollStr.equals ("")||regdStr.equals ("")||streamStr.equals ("--Select Stream--")||branchStr.equals ("--Select Branch--")||semStr.equals ("--Select Semester--")||hobbyStr.equals ("--Select Hobby--")||addrStr.equals ("")||genderStr.equals (""))
						warningL.setText ("Incomplete Information!");
					else
					{
						try
						{
							PreparedStatement ps = conn.prepareStatement ("update StudentSystem set name=?, father=?, mother=?, stream=?, branch=?, semester=?, phone=?, email=?, gender=?, hobby=?, dob=?, address=? where regdno=?");
							ps.setString (1,nameStr);
							ps.setString (2,fatherStr);
							ps.setString (3,motherStr);
							ps.setString (4,streamStr);
							ps.setString (5,branchStr);
							ps.setString (6,semStr);
							ps.setLong (7,pho);
							ps.setString (8,emailStr);
							ps.setString (9,genderStr);
							ps.setString (10,hobbyStr);
							SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
							java.util.Date ud=sdf.parse(dobStr);
							java.sql.Date sqd=new java.sql.Date(ud.getTime());
							ps.setDate (11,sqd);
							ps.setString (12,addrStr);
							ps.setLong (13,Long.parseLong (regdNoTf.getText ()));
							int status = ps.executeUpdate ();
							if (status != 0)
							{
								setVisible (false);
								dispose ();
							}
							else
								warningL.setText ("Error!");
						}
						catch (Exception e)
						{
							e.printStackTrace ();
						}
					}
				}
			}
			else
			{
				if((m.equals("02")&&dt.equals("31"))||(m.equals("02")&&dt.equals("30"))||(m.equals("02")&&dt.equals("29")))
					warningL.setText ("Please enter a valid DOB!");
				if((m.equals("04")&&dt.equals("31"))||(m.equals("06")&&dt.equals("31"))||(m.equals("09")&&dt.equals("31"))||(m.equals("11")&&dt.equals("31")))
					warningL.setText ("Please enter a valid DOB!");
				else
				{
					if (nameStr.equals ("")||fatherStr.equals ("")||motherStr.equals ("")||rollStr.equals ("")||regdStr.equals ("")||streamStr.equals ("--Select Stream--")||branchStr.equals ("--Select Branch--")||semStr.equals ("--Select Semester--")||hobbyStr.equals ("--Select Hobby--")||addrStr.equals ("")||genderStr.equals (""))
						warningL.setText ("Incomplete Information!");
					else
					{
						try
						{
							PreparedStatement ps = conn.prepareStatement ("update StudentSystem set name=?, father=?, mother=?, stream=?, branch=?, semester=?, phone=?, email=?, gender=?, hobby=?, dob=?, address=? where regdno=?");
							ps.setString (1,nameStr);
							ps.setString (2,fatherStr);
							ps.setString (3,motherStr);
							ps.setString (4,streamStr);
							ps.setString (5,branchStr);
							ps.setString (6,semStr);
							ps.setLong (7,pho);
							ps.setString (8,emailStr);
							ps.setString (9,genderStr);
							ps.setString (10,hobbyStr);
							SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
							java.util.Date ud=sdf.parse(dobStr);
							java.sql.Date sqd=new java.sql.Date(ud.getTime());
							ps.setDate (11,sqd);
							ps.setString (12,addrStr);
							ps.setLong (13,Long.parseLong (regdNoTf.getText ()));
							int status = ps.executeUpdate ();
							if (status != 0)
							{
								setVisible (false);
								dispose ();
							}
							else
								warningL.setText ("Error!");
						}
						catch (Exception e)
						{
							e.printStackTrace ();
						}
					}
				}
			}
		}
	}
}

class SearchFrame extends JFrame
{
	static Container container;
	static JPanel mainPanel;
	static JButton regdNoSearch, rollNoSearch, branchSearch, streamSearch, nameSearch, ok;
	static JTextField regdRollName;
	static JComboBox branchStream;
	static Font f1;
	static int flag = 0;
	static Connection conn;
	static JTable viewData;
	static JTextArea viewData2;
	SearchFrame ()
	{
		//fonts
		f1 = new Font ("Eras Medium ITC",Font.PLAIN,15);

		//container
		container = this.getContentPane ();
		container.setLayout (null);

		//main panel
		mainPanel = new JPanel ();
		mainPanel.setBounds (0,0,1250,700);
		mainPanel.setLayout (null);

		//buttons
		regdNoSearch = new JButton ("Registration No");
		regdNoSearch.setFont (f1);
		regdNoSearch.setBounds (5,5,150,35);
		regdNoSearch.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				regdNoSearchAction (ae);
			}
		});

		rollNoSearch = new JButton ("Roll No");
		rollNoSearch.setFont (f1);
		rollNoSearch.setBounds (160,5,150,35);
		rollNoSearch.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				rollNoSearchAction (ae);
			}
		});

		branchSearch = new JButton ("Branch");
		branchSearch.setFont (f1);
		branchSearch.setBounds (315,5,150,35);
		branchSearch.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				branchSearchAction (ae);
			}
		});

		streamSearch = new JButton ("Stream");
		streamSearch.setFont (f1);
		streamSearch.setBounds (470,5,150,35);
		streamSearch.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				streamSearchAction (ae);
			}
		});

		nameSearch = new JButton ("Name");
		nameSearch.setFont (f1);
		nameSearch.setBounds (625,5,150,35);
		nameSearch.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				nameSearchAction (ae);
			}
		});

		ok = new JButton ("Search");
		ok.setFont (f1);
		ok.setBounds (1125,5,100,35);
		ok.addActionListener (new ActionListener ()
		{
			public void actionPerformed (ActionEvent ae)
			{
				okAction (ae);
			}
		});

		//comboboxes
		branchStream = new JComboBox ();
		branchStream.setBounds (800,5,300,35);
		branchStream.setFont (f1);

		//textfields
		regdRollName = new JTextField ();
		regdRollName.setBounds (800,5,300,35);
		regdRollName.setFont (f1);

		//jtextarea
		viewData2 = new JTextArea ();
		viewData2.setBounds (5,45,1240,650);
		viewData2.setFont (f1);
		viewData2.setEditable (false);
		viewData2.setLineWrap(true);
		viewData2.setWrapStyleWord(true);
		viewData2.setRows (100);
		viewData2.setColumns (1000);

		//adding the main panel to the container
		container.add (mainPanel);

		//adding the components to the main panel
		mainPanel.add (regdNoSearch);
		mainPanel.add (rollNoSearch);
		mainPanel.add (nameSearch);
		mainPanel.add (branchSearch);
		mainPanel.add (streamSearch);

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

	public void regdNoSearchAction (ActionEvent ae)
	{
		flag = 1;
		mainPanel.add (regdRollName);
		mainPanel.remove (branchStream);
		mainPanel.add (ok);
		mainPanel.revalidate ();
		mainPanel.repaint ();
	}

	public void rollNoSearchAction (ActionEvent ae)
	{
		flag = 2;
		mainPanel.add (regdRollName);
		mainPanel.remove (branchStream);
		mainPanel.add (ok);
		mainPanel.revalidate ();
		mainPanel.repaint ();
	}

	public void branchSearchAction (ActionEvent ae)
	{
		flag = 3;
		mainPanel.remove (branchStream);
		branchStream.removeAllItems ();
		branchStream.addItem ("--Select Branch--");
		branchStream.addItem("Architecture");
		branchStream.addItem("Chemical");
		branchStream.addItem("Civil");
		branchStream.addItem("CSE");
		branchStream.addItem("CSE (SSC)");
		branchStream.addItem("CS & IS");
		branchStream.addItem("Electrical");
		branchStream.addItem("E & TC");
		branchStream.addItem("E & TC (SSC)");
		branchStream.addItem("EC & M");
		branchStream.addItem("Energy System");
		branchStream.addItem("Geotechnical");
		branchStream.addItem("IM");
		branchStream.addItem("MCA");
		branchStream.addItem("Mechanical");
		branchStream.addItem("MSD");
		branchStream.addItem("MME");
		branchStream.addItem("Metallurgical");
		branchStream.addItem("PED");
		branchStream.addItem("PSE");
		branchStream.addItem("Production");
		branchStream.addItem("Structural");
		branchStream.addItem("Thermal");
		branchStream.addItem("Transportation");
		branchStream.addItem("WCT");
		mainPanel.add (branchStream);
		mainPanel.remove (regdRollName);
		mainPanel.add (ok);
		mainPanel.revalidate ();
		mainPanel.repaint ();
	}

	public void streamSearchAction (ActionEvent ae)
	{
		flag = 4;
		mainPanel.remove (branchStream);
		branchStream.removeAllItems ();
		branchStream.addItem ("--Select Stream--");
		branchStream.addItem ("BTech");
		branchStream.addItem ("MTech");
		branchStream.addItem ("Diploma");
		branchStream.addItem ("MSc");
		branchStream.addItem ("MCA");
		branchStream.addItem ("BArch");
		mainPanel.add (branchStream);
		mainPanel.remove (regdRollName);
		mainPanel.add (ok);
		mainPanel.revalidate ();
		mainPanel.repaint ();
	}

	public void nameSearchAction (ActionEvent ae)
	{
		flag = 5;
		mainPanel.add (regdRollName);
		mainPanel.add (branchStream);
		mainPanel.add (ok);
		mainPanel.revalidate ();
		mainPanel.repaint ();
	}

	public void okAction (ActionEvent ae)
	{	try{
		String name = "", regd = "", roll = "", branch = "", stream = "";
		if (flag == 1)
		{
			regd = regdRollName.getText ();
			PreparedStatement ps = conn.prepareStatement ("select * from StudentSystem where regdno=?");
			ps.setString (1,regd);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
			{
				String g = "";
				if (rs.getString ("gender").equals ("M"))
					g = "Male";
				else if (rs.getString ("gender").equals ("F"))
					g = "Female";
				else if (rs.getString ("gender").equals ("O"))
					g = "Other";
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				java.sql.Date sqd = rs.getDate ("dob");
				java.util.Date ud = (java.util.Date)sqd;
				String d = sdf.format (ud);
				viewData2.setText ("NAME: "+rs.getString ("name")+"\tROLL NO: "+rs.getInt ("rollno")+"\tREGD NO: "+rs.getLong ("regdno")+"\tFATHER'S NAME: "+rs.getString ("father")+"\tMOTHER'S NAME: "+rs.getString ("mother")+"\tSTREAM: "+rs.getString ("stream")+"\tBRANCH: "+rs.getString ("branch")+"\tSEMESTER: "+rs.getString ("semester")+"\tPHONE: "+rs.getLong ("phone")+"\tEMAIL: "+rs.getString ("email")+"\tGENDER: "+g+"\tHOBBY: "+rs.getString ("hobby")+"\tDOB: "+d+"\tADDRESS: "+rs.getString ("address"));
				mainPanel.add (viewData2);
				mainPanel.revalidate ();
				mainPanel.repaint ();
			}
			else
				viewData2.setText ("Not Found!");
		}
		else if (flag == 2)
		{
			roll = regdRollName.getText ();
			PreparedStatement ps = conn.prepareStatement ("select * from StudentSystem where rollno=?");
			ps.setString (1,roll);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
			{
				String g = "";
				if (rs.getString ("gender").equals ("M"))
					g = "Male";
				else if (rs.getString ("gender").equals ("F"))
					g = "Female";
				else if (rs.getString ("gender").equals ("O"))
					g = "Other";
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				java.sql.Date sqd = rs.getDate ("dob");
				java.util.Date ud = (java.util.Date)sqd;
				String d = sdf.format (ud);
				viewData2.setText ("NAME: "+rs.getString ("name")+"\tROLL NO: "+rs.getInt ("rollno")+"\tREGD NO: "+rs.getLong ("regdno")+"\tFATHER'S NAME: "+rs.getString ("father")+"\tMOTHER'S NAME: "+rs.getString ("mother")+"\tSTREAM: "+rs.getString ("stream")+"\tBRANCH: "+rs.getString ("branch")+"\tSEMESTER: "+rs.getString ("semester")+"\tPHONE: "+rs.getLong ("phone")+"\tEMAIL: "+rs.getString ("email")+"\tGENDER: "+g+"\tHOBBY: "+rs.getString ("hobby")+"\tDOB: "+d+"\tADDRESS: "+rs.getString ("address"));
				mainPanel.add (viewData2);
				mainPanel.revalidate ();
				mainPanel.repaint ();
			}
			else
				viewData2.setText ("Not Found!");
		}
		else if (flag == 3)
		{
			branch = (String) branchStream.getSelectedItem ();
			PreparedStatement ps = conn.prepareStatement ("select * from StudentSystem where branch=?");
			ps.setString (1,branch);
			ResultSet rs = ps.executeQuery ();
			while (rs.next ())
			{
				String g = "";
				if (rs.getString ("gender").equals ("M"))
					g = "Male";
				else if (rs.getString ("gender").equals ("F"))
					g = "Female";
				else if (rs.getString ("gender").equals ("O"))
					g = "Other";
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				java.sql.Date sqd = rs.getDate ("dob");
				java.util.Date ud = (java.util.Date)sqd;
				String d = sdf.format (ud);
				viewData2.append ("NAME: "+rs.getString ("name")+"\tROLL NO: "+rs.getInt ("rollno")+"\tREGD NO: "+rs.getLong ("regdno")+"\tFATHER'S NAME: "+rs.getString ("father")+"\tMOTHER'S NAME: "+rs.getString ("mother")+"\tSTREAM: "+rs.getString ("stream")+"\tBRANCH: "+rs.getString ("branch")+"\tSEMESTER: "+rs.getString ("semester")+"\tPHONE: "+rs.getLong ("phone")+"\tEMAIL: "+rs.getString ("email")+"\tGENDER: "+g+"\tHOBBY: "+rs.getString ("hobby")+"\tDOB: "+d+"\tADDRESS: "+rs.getString ("address")+"\n\n");
				mainPanel.add (viewData2);
				mainPanel.revalidate ();
				mainPanel.repaint ();
			}
		}
		else if (flag == 4)
		{
			stream = (String) branchStream.getSelectedItem ();
			PreparedStatement ps = conn.prepareStatement ("select * from StudentSystem where stream=?");
			ps.setString (1,stream);
			ResultSet rs = ps.executeQuery ();
			while (rs.next ())
			{
				String g = "";
				if (rs.getString ("gender").equals ("M"))
					g = "Male";
				else if (rs.getString ("gender").equals ("F"))
					g = "Female";
				else if (rs.getString ("gender").equals ("O"))
					g = "Other";
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				java.sql.Date sqd = rs.getDate ("dob");
				java.util.Date ud = (java.util.Date)sqd;
				String d = sdf.format (ud);
				viewData2.append ("NAME: "+rs.getString ("name")+"\tROLL NO: "+rs.getInt ("rollno")+"\tREGD NO: "+rs.getLong ("regdno")+"\tFATHER'S NAME: "+rs.getString ("father")+"\tMOTHER'S NAME: "+rs.getString ("mother")+"\tSTREAM: "+rs.getString ("stream")+"\tBRANCH: "+rs.getString ("branch")+"\tSEMESTER: "+rs.getString ("semester")+"\tPHONE: "+rs.getLong ("phone")+"\tEMAIL: "+rs.getString ("email")+"\tGENDER: "+g+"\tHOBBY: "+rs.getString ("hobby")+"\tDOB: "+d+"\tADDRESS: "+rs.getString ("address")+"\n\n");
				mainPanel.add (viewData2);
				mainPanel.revalidate ();
				mainPanel.repaint ();
			}
		}
		else if (flag == 5)
		{
			name = regdRollName.getText ();
			PreparedStatement ps = conn.prepareStatement ("select * from StudentSystem where name=?");
			ps.setString (1,name);
			ResultSet rs = ps.executeQuery ();
			if (rs.next ())
			{
				String g = "";
				if (rs.getString ("gender").equals ("M"))
					g = "Male";
				else if (rs.getString ("gender").equals ("F"))
					g = "Female";
				else if (rs.getString ("gender").equals ("O"))
					g = "Other";
				SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
				java.sql.Date sqd = rs.getDate ("dob");
				java.util.Date ud = (java.util.Date)sqd;
				String d = sdf.format (ud);
				viewData2.setText ("NAME: "+rs.getString ("name")+"\tROLL NO: "+rs.getInt ("rollno")+"\tREGD NO: "+rs.getLong ("regdno")+"\tFATHER'S NAME: "+rs.getString ("father")+"\tMOTHER'S NAME: "+rs.getString ("mother")+"\tSTREAM: "+rs.getString ("stream")+"\tBRANCH: "+rs.getString ("branch")+"\tSEMESTER: "+rs.getString ("semester")+"\tPHONE: "+rs.getLong ("phone")+"\tEMAIL: "+rs.getString ("email")+"\tGENDER: "+g+"\tHOBBY: "+rs.getString ("hobby")+"\tDOB: "+d+"\tADDRESS: "+rs.getString ("address"));
				mainPanel.add (viewData2);
				mainPanel.revalidate ();
				mainPanel.repaint ();
			}
			else
				viewData2.setText ("Not Found!");
		}
		}
		catch (Exception e)
		{
			e.printStackTrace ();
		}
	}
}

public class StudentSystem
{
	public static void main (String[] args)
	{
		StudentFrame sFrame = new StudentFrame ();
		sFrame.setVisible (true);
		sFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		sFrame.setBounds (0,0,1250,700);
	}
}