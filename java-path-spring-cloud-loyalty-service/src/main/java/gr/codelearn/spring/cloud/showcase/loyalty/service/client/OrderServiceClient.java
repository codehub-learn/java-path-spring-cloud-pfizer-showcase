package gr.codelearn.spring.cloud.showcase.loyalty.service.client;

import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "order-service", path = "/orders")
public interface OrderServiceClient {
	@GetMapping(headers = {"action=countByCustomer"})
	ResponseEntity<ApiResponse<Long>> countByCustomer(final String email);

	@GetMapping(headers = {"action=countByNotNullCouponCodeAndEmail"})
	ResponseEntity<ApiResponse<Long>> countByNotNullCouponCodeAndEmail(final String email);
}
