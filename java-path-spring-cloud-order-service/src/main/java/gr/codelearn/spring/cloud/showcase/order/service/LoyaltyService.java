package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.order.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;

import java.util.Optional;

public interface LoyaltyService {
	Optional<Coupon> apply(Order order);

	void declare(Coupon coupon);
}
