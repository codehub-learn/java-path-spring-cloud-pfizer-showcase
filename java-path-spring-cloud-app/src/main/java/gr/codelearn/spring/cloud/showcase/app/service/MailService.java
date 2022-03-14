package gr.codelearn.spring.cloud.showcase.app.service;

import java.io.IOException;

public interface MailService {
	void sendEmail(String toSender, String subject, String emailBody) throws IOException;
}
