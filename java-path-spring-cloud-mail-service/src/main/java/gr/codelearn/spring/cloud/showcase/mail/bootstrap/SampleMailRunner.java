package gr.codelearn.spring.cloud.showcase.mail.bootstrap;

import gr.codelearn.spring.cloud.showcase.core.base.BaseComponent;
import gr.codelearn.spring.cloud.showcase.mail.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("sample-mail")
@Component
@RequiredArgsConstructor
public class SampleMailRunner extends BaseComponent implements CommandLineRunner {
	private final MailService mailService;

	@Override
	public void run(String... args) throws Exception {
		mailService.sendEmail("c.giannacoulis@codehub.gr", "First sample subject",
							  "Hello from a sample <b>Spring Boot</b>.");

		mailService.sendEmail("c.giannacoulis@codehub.gr", "Second sample subject",
							  "<p>Hello from a sample <b>Spring Boot</b>.</p><p>Thank you</p>");
	}
}
