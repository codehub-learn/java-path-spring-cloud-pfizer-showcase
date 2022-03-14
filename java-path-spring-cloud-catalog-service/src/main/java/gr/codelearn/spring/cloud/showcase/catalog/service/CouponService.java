package gr.codelearn.spring.cloud.showcase.catalog.service;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.catalog.service.rule.Rule;

public interface CouponService extends BaseService<Coupon, Long> {
	Coupon generate(Rule rule);
}
