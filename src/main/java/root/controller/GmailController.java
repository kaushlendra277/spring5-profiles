package root.controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// https://stackoverflow.com/questions/22841632/how-i-send-email-without-authentication-using-java
// https://www.javatpoint.com/example-of-sending-email-using-java-mail-api
public class GmailController {
	
	@Autowired 
	private Session session;
	
	@GetMapping
	public ResponseEntity<Void> send() {
		try {
			sendMail("kaushlendra277@gmail.com");
			System.out.println("Email Sent");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().build();
	}
	
	private void sendMail(String... to) throws Exception {
		try {
			MimeMessage message = new MimeMessage(session);
			//message.setFrom(new InternetAddress("xoriantkpmglocal@gmail.com"));
			message.setFrom(session.getProperty("mail.smtp.username"));
			InternetAddress[] internetAddresses = new InternetAddress[to.length]; 
			for(int i = 0; i < to.length; i++) {
				internetAddresses[i] = new InternetAddress(to[i]);
			}
			message.setRecipients(RecipientType.TO, internetAddresses);
			message.setSubject("Notification");
			message.setText("Successful!", "UTF-8"); // as "text/plain"
			message.setSentDate(new Date());
			Transport.send(message);	
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
}
