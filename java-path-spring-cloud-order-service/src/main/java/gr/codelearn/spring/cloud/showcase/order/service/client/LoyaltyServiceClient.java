package gr.codelearn.spring.cloud.showcase.order.service.client;

import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CouponResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "loyalty-service", path = "/loyalty")
public interface LoyaltyServiceClient {
	@PostMapping(headers = {"action=apply"})
	ResponseEntity<ApiResponse<CouponResource>> apply(@Valid @RequestBody OrderResource order);

	@PostMapping(headers = {"action=declare"})
	void declare(@Valid @RequestBody CouponResource coupon);
}
