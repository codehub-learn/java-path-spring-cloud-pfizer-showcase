package gr.codelearn.spring.cloud.showcase.app.controller;

import gr.codelearn.spring.cloud.showcase.app.domain.Customer;
import gr.codelearn.spring.cloud.showcase.app.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.app.mapper.CustomerMapper;
import gr.codelearn.spring.cloud.showcase.app.service.BaseService;
import gr.codelearn.spring.cloud.showcase.app.service.CustomerService;
import gr.codelearn.spring.cloud.showcase.app.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.cloud.showcase.app.transfer.resource.CustomerResource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@WebEndpoint(id = "customerStatistics") // Actuator endpoint
@RequestMapping("/customers")
public class CustomerController extends BaseController<Customer, CustomerResource> {
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
		return ResponseEntity.ok(
				ApiResponse.<CustomerResource>builder().data(getMapper().toResource(customerService.findByEmail(email)))
						   .build());
	}

	@GetMapping(headers = "action=customersPurchasedMostExpensiveProduct")
	public ResponseEntity<ApiResponse<List<KeyValue<String, Long>>>> findCustomersPurchasedMostExpensiveProduct() {
		final List<KeyValue<String, Long>> customersPurchasedMostExpensiveProductList =
				customerService.findCustomersPurchasedMostExpensiveProduct();
		return ResponseEntity.ok(
				ApiResponse.<List<KeyValue<String, Long>>>builder().data(customersPurchasedMostExpensiveProductList)
						   .build());
	}

	@ReadOperation
	public List<KeyValue<String, BigDecimal>> getStatistics() {
		return customerService.getStatistics();
	}
}
