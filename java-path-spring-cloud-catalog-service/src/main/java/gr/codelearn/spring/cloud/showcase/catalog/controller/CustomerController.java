package gr.codelearn.spring.cloud.showcase.catalog.controller;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Customer;
import gr.codelearn.spring.cloud.showcase.catalog.service.BaseService;
import gr.codelearn.spring.cloud.showcase.catalog.service.CustomerService;
import gr.codelearn.spring.cloud.showcase.catalog.transfer.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController extends AbstractController<Customer> {
	private final CustomerService customerService;

	@Override
	public BaseService<Customer, Long> getBaseService() {
		return customerService;
	}

	@GetMapping(params = {"email"})
	public ResponseEntity<ApiResponse<Customer>> findByEmail(@RequestParam String email) {
		return ResponseEntity.ok(ApiResponse.<Customer>builder().data(customerService.findByEmail(email)).build());
	}
}
