package gr.codelearn.spring.cloud.showcase.customer.service;

import gr.codelearn.spring.cloud.showcase.customer.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.customer.domain.Order;

import java.util.Optional;

public interface LoyaltyService {
	Optional<Coupon> apply(Order order);

	void declare(Coupon coupon);
}
