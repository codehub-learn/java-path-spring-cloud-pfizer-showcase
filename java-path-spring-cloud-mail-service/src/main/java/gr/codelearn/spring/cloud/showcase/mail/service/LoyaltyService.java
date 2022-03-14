package gr.codelearn.spring.cloud.showcase.mail.service;

import gr.codelearn.spring.cloud.showcase.mail.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.mail.domain.Order;

import java.util.Optional;

public interface LoyaltyService {
	Optional<Coupon> apply(Order order);

	void declare(Coupon coupon);
}
