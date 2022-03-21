package gr.codelearn.spring.cloud.showcase.loyalty.service;

import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.Coupon;

import java.util.Optional;

public interface LoyaltyService {
	Optional<Coupon> apply(OrderResource order);

	void declare(Coupon coupon);
}
