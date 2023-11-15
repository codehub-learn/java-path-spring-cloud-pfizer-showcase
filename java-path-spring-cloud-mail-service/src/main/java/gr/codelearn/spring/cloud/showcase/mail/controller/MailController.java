package gr.codelearn.spring.cloud.showcase.mail.controller;

import gr.codelearn.spring.cloud.showcase.mail.service.MailService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mail")
public class MailController {
	private final MailService mailService;

	@PostMapping(headers = {"action=send"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void send(@Email @RequestParam("sender") final String sender, @RequestParam("subject") final String subject,
					 @RequestParam("emailBody") final String emailBody) throws IOException {
		mailService.sendEmail(sender, subject, emailBody);
	}
}
