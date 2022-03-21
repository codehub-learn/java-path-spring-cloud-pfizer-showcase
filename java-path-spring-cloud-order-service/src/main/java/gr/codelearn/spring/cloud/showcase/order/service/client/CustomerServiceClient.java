package gr.codelearn.spring.cloud.showcase.order.service.client;

import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customer-service", path = "/customers")
public interface CustomerServiceClient {
	@GetMapping("/{id}")
	ResponseEntity<ApiResponse<CustomerResource>> findById(@PathVariable("id") final Long id);

	@GetMapping(params = {"email"})
	ResponseEntity<ApiResponse<CustomerResource>> findByEmail(@RequestParam String email);

	@GetMapping("/lb")
	ResponseEntity<ApiResponse<String>> checkLoadBalancing();
}
