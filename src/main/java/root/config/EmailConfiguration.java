package root.config;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {
	
	@Value("${spring.profiles.active:default}")
	private String profile;

	@Value("${mail.smtp.username}")
	private String emailId;

	@Value("${mail.smtp.password:xxxx}")
	private String password;

	@Value("${mail.smtp.host}")
	private String host;

	@Value("${mail.smtp.port}")
	private String port;

	@Bean
	public Session session() {
		
		Session session = null;
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.username", emailId);
		if (profile.equalsIgnoreCase("qa")) {
			session =  Session.getDefaultInstance(properties);
		} else {
			properties.put("mail.smtp.auth", "true");
			// this property is set to overcome "Must issue a STARTTLS command first" exception
			// https://stackoverflow.com/questions/10509699/must-issue-a-starttls-command-first
			properties.put("mail.smtp.starttls.enable", "true");
			session = Session.getDefaultInstance(properties, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailId, password);
				}
			});
		}
		return session;
	}
}
