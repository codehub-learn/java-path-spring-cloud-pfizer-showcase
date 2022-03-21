package gr.codelearn.spring.cloud.showcase.customer.controller;

import gr.codelearn.spring.cloud.showcase.core.base.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.controller.AbstractController;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.customer.domain.Customer;
import gr.codelearn.spring.cloud.showcase.customer.mapper.CustomerMapper;
import gr.codelearn.spring.cloud.showcase.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerResourceController extends AbstractController<Customer, CustomerResource> {
	private final CustomerService customerService;
	private final CustomerMapper customerMapper;
	private final Environment env;

	@PostConstruct
	private void init() {
		// Sample logging attempt
		logger.info("Leasing renewal timeout is set to {} seconds.",
					env.getProperty("eureka.instance.lease-renewal-interval-in-seconds"));
	}

	@Override
	public BaseService<Customer, Long> getBaseService() {
		return customerService;
	}

	@Override
	public BaseMapper<Customer, CustomerResource> getMapper() {
		return customerMapper;
	}

	@GetMapping(params = {"email"})
	public ResponseEntity<ApiResponse<CustomerResource>> findByEmail(@RequestParam String email) {
		return ResponseEntity.ok(ApiResponse.<CustomerResource>builder()
										 .data(customerMapper.toResource(customerService.findByEmail(email))).build());
	}

	@GetMapping("/lb")
	public ResponseEntity<ApiResponse<String>> checkLoadBalancing() {
		return ResponseEntity.ok(ApiResponse.<String>builder().data(String.format(
						"Load balancing demonstration by accepting requests on port %s.", env.getProperty("server.port")))
										 .build());
	}
}
