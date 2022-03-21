package gr.codelearn.spring.cloud.showcase.loyalty.controller;

import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CouponResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.loyalty.mapper.CouponMapper;
import gr.codelearn.spring.cloud.showcase.loyalty.service.LoyaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loyalty")
public class LoyaltyController {
	private final LoyaltyService loyaltyService;
	private final CouponMapper couponMapper;

	@PostMapping(headers = {"action=apply"})
	public ResponseEntity<ApiResponse<CouponResource>> apply(@Valid @RequestBody OrderResource order) {
		var loyaltyResult = loyaltyService.apply(order);

		Coupon coupon = loyaltyResult.orElse(null);
		return ResponseEntity.ok(ApiResponse.<CouponResource>builder().data(couponMapper.toResource(coupon)).build());
	}

	@PostMapping(headers = {"action=declare"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void declare(@Valid @RequestBody CouponResource coupon) {
		loyaltyService.declare(couponMapper.toDomain(coupon));
	}
}
