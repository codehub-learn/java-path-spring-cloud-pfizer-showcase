package gr.codelearn.spring.cloud.showcase.order.service.client;

import jakarta.validation.constraints.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mail-service", path = "/mail")
public interface MailServiceClient {
	@PostMapping(headers = {"action=send"})
	void send(@Email @RequestParam("sender") final String sender, @RequestParam("subject") final String subject,
			  @RequestParam("emailBody") final String emailBody);
}
