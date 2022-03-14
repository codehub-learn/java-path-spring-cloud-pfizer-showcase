package gr.codelearn.spring.cloud.showcase.mail.service;

import gr.codelearn.spring.cloud.showcase.mail.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.mail.service.rule.Rule;

public interface CouponService extends BaseService<Coupon, Long> {
	Coupon generate(Rule rule);
}
