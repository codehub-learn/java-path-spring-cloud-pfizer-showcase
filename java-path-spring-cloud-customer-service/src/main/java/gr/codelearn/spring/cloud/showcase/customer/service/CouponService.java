package gr.codelearn.spring.cloud.showcase.customer.service;

import gr.codelearn.spring.cloud.showcase.customer.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.customer.service.rule.Rule;

public interface CouponService extends BaseService<Coupon, Long> {
	Coupon generate(Rule rule);
}
