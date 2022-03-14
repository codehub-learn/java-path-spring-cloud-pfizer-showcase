package gr.codelearn.spring.cloud.showcase.catalog.service;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.catalog.domain.Order;

import java.util.Optional;

public interface LoyaltyService {
	Optional<Coupon> apply(Order order);

	void declare(Coupon coupon);
}
