package gr.codelearn.spring.cloud.showcase.mail.service;

import java.io.IOException;

public interface MailService {
	void sendEmail(String recipient, String subject, String emailBody) throws IOException;
}
