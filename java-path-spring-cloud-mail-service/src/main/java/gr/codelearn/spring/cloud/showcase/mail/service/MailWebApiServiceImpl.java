package gr.codelearn.spring.cloud.showcase.mail.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import gr.codelearn.spring.cloud.showcase.core.base.BaseComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Profile("mail-webapi")
@Service
@RequiredArgsConstructor
public class MailWebApiServiceImpl extends BaseComponent implements MailService {
	private final static String EMAIL_TYPE = "text/html";

	private final Environment env;
	private final SendGrid sendGrid;

	public void sendEmail(String recipient, String subject, String emailBody) {
		Email from = new Email(env.getProperty("spring.sendgrid.properties.username"),
							   env.getProperty("spring.sendgrid.properties.sender.name"));
		Email to = new Email(recipient);
		Content content = new Content(EMAIL_TYPE, emailBody);
		Mail mail = new Mail(from, subject, to, content);

		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			sendGrid.api(request);
			logger.debug("Email sent to '{}' successfully.", recipient);
		} catch (IOException ex) {
			logger.error("Unable to send mail to '{}'.", recipient, ex);
		}
	}
}
