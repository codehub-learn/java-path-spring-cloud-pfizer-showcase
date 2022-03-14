package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.app.service.rule.Rule;

public interface CouponService extends BaseService<Coupon, Long> {
	Coupon generate(Rule rule);
}
