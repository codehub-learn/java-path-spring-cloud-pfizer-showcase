package gr.codelearn.spring.cloud.showcase.customer.controller;

import gr.codelearn.spring.cloud.showcase.core.controller.BaseController;
import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.customer.domain.Customer;
import gr.codelearn.spring.cloud.showcase.customer.mapper.CustomerMapper;
import gr.codelearn.spring.cloud.showcase.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerResourceController extends BaseController<Customer, CustomerResource> {
	private final CustomerService customerService;
	private final CustomerMapper customerMapper;

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
											.data(getMapper().toResource(customerService.findByEmail(email)))
											.build());
	}
}
