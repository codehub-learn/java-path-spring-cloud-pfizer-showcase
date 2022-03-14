package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.order.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.order.service.rule.Rule;

public interface CouponService extends BaseService<Coupon, Long> {
	Coupon generate(Rule rule);
}
