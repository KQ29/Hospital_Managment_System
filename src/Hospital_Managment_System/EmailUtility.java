package Hospital_Managment_System;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailUtility {
	public static void sendEmail(String recipientEmail, String subject, String messageText) throws MessagingException {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server
		properties.put("mail.smtp.port", "587"); // Port for TLS/STARTTLS

		final String username = ""; // Your Gmail address
		final String password = ""; // Your Gmail password or app-specific password

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
		message.setSubject(subject);
		message.setText(messageText);

		Transport.send(message);
	}
}
