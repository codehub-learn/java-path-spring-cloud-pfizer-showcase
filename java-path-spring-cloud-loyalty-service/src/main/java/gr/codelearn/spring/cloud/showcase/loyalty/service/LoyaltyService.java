package gr.codelearn.spring.cloud.showcase.loyalty.service;

import gr.codelearn.spring.cloud.showcase.loyalty.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.Order;

import java.util.Optional;

public interface LoyaltyService {
	Optional<Coupon> apply(Order order);

	void declare(Coupon coupon);
}
