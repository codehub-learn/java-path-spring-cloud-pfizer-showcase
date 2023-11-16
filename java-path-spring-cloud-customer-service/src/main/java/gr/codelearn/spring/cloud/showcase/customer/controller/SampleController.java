package gr.codelearn.spring.cloud.showcase.customer.controller;

import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class SampleController {
	private final Environment env;

	@GetMapping("/lb")
	public ResponseEntity<ApiResponse<String>> checkLoadBalancing() {
		return ResponseEntity.ok(ApiResponse.<String>builder()
											.data(String.format(
													"Load balancing demonstration by accepting requests on port %s.",
													env.getProperty("server.port")))
											.build());
	}
}
