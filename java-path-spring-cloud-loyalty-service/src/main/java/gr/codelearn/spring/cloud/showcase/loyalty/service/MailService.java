package gr.codelearn.spring.cloud.showcase.loyalty.service;

import java.io.IOException;

public interface MailService {
	void sendEmail(String toSender, String subject, String emailBody) throws IOException;
}
