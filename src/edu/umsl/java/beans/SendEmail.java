package edu.umsl.java.beans;

// File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

   public void email(String subject, String body, String to) throws MessagingException {
	   
//	   try {
//		   String host = "smtp.gmail.com";
//		   String from = "bearcowbearcow";
//		   String[] to = {"kingdomhearts227@yahoo.com"};
//		   Properties props = System.getProperties();
//		   props.put("mail.smtp.host", host);
//		   props.put("mail.smtp.user", from);
//		   props.put("mail.smtp.password", "");
//		   props.put("mail.smtp.port", "587"); // 587 is the port number of yahoo mail
//		   props.put("mail.smtp.auth", "true");
//
//		   Session session = Session.getDefaultInstance(props, null);
//		   MimeMessage message = new MimeMessage(session);
//		message.setFrom(new InternetAddress(from));
//		InternetAddress[] to_address = new InternetAddress[to.length];
//		   // To get the array of addresses
//		   for(int i = 0; i < to.length; i++) {
//		       to_address[i] = new InternetAddress(to[i]);
//		       i++;
//		   }
//		   System.out.println(Message.RecipientType.TO);
//		   for(int i = 0; i < to.length; i++) {
//		       message.addRecipient(Message.RecipientType.TO, to_address[i]);
//		       i++;
//		   }
//		   message.setSubject("sending in a group");
//		   message.setText("Welcome to JavaMail");
//		   // alternately, to send HTML mail:
//		   // message.setContent("<p>Welcome to JavaMail</p>", "text/html");
//		   Transport transport = session.getTransport("smtp");
//		   transport.connect("smtp.mail.yahoo.co.in", "user name", "asdfgh");
//		   transport.sendMessage(message, message.getAllRecipients());
//		   transport.close();
//	} catch (AddressException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	} catch (MessagingException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

	   
	   
//	   System.out.println("SendEmail");
//      // Recipient's email ID needs to be mentioned.
//      String to = "kingdomhearts227@yahoo.com";
//
//      // Sender's email ID needs to be mentioned
//      String from = "bearcowbearcow@gmail.com";
//
//      // Assuming you are sending email from localhost
//      String host = "localhost";
//
//      // Get system properties
//      Properties properties = System.getProperties();
//
//      // Setup mail server
//      properties.setProperty("mail.smtp.host", host);
//
//      // Get the default Session object.
//      Session session = Session.getDefaultInstance(properties);
//
//      try {
//         // Create a default MimeMessage object.
//         MimeMessage message = new MimeMessage(session);
//
//         // Set From: header field of the header.
//         message.setFrom(new InternetAddress(from));
//
//         // Set To: header field of the header.
//         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//
//         // Set Subject: header field
//         message.setSubject("This is the Subject Line!");
//
//         // Now set the actual message
//         message.setText("This is actual message");
//
//         // Send message
//         Transport.send(message);
//         System.out.println("Sent message successfully....");
//      }catch (MessagingException mex) {
//         mex.printStackTrace();
//      }
	   
//	   String subject = "Subject";
//	   Boolean bodyIsHTML = false;
//	   String body = "Body";
//	   String to = "bearcowbearcow@gmail.com";
//	   String from = "kingdomhearts227@yahoo.com";
//	// 1 - get a mail session
//       Properties props = new Properties();
//       props.put("mail.transport.protocol", "smtps");
//       props.put("mail.smtps.host", "smtp.mail.yahoo.com");
//       props.put("mail.smtps.port", 465);
//       props.put("mail.smtps.auth", "true");
//       Session session = Session.getDefaultInstance(props);
//       session.setDebug(true);
//
//       // 2 - create a message
//       Message message = new MimeMessage(session);
//       message.setSubject(subject);
//       if (bodyIsHTML) {
//           message.setContent(body, "text/html");
//       } else {
//           message.setText(body);
//       }
//
//       // 3 - address the message
//       Address fromAddress = new InternetAddress(from);
//       Address toAddress = new InternetAddress(to);
//       message.setFrom(fromAddress);
//       message.setRecipient(Message.RecipientType.TO, toAddress);
//
//       // 4 - send the message
//       Transport transport = session.getTransport();
//       transport.connect("kingdomhearts227@yahoo.com", "");
//       transport.sendMessage(message, message.getAllRecipients());
//       transport.close();
	   
	   final String user = "";
		final String password = "";

		String from = "bearcowbearcow@gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
   }
	   
	   
}