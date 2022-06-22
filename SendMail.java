import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import javax.activation.*;

public class SendMail {
	public static void main (String[] args) {
		String from= "prateekshyapriyadarshini@gmail.com";
		String pswd= "soni1999";
		String to= "pratikparidabubu@gmail.com";
		String host= "smtp.gmail.com";
		Properties properties= new Properties ();
		properties.put ("mail.smtp.host",host);
		properties.put ("mail.smtp.auth",true);
		properties.put ("mail.smtp.socketFactory.port","465");
		properties.put ("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		properties.put ("mail.smtp.port","465");
		Session session= Session.getDefaultInstance (properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from,pswd);
			}
		});
		try {
			MimeMessage msg= new MimeMessage (session);
			msg.setFrom (new InternetAddress (from));
			msg.addRecipient (Message.RecipientType.TO, new InternetAddress (to));
			msg.setSubject ("Sent through Java Mail API");
			msg.setText ("My message was sent successfully! :-P :-D");
			Transport.send (msg);
			System.out.println ("Your message was sent successfully to "+to);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
}