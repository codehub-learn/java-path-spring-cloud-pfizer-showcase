package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.app.repository.CouponRepository;
import gr.codelearn.spring.cloud.showcase.app.service.rule.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl extends BaseServiceImpl<Coupon> implements CouponService {
	private final CouponRepository couponRepository;

	@Override
	public JpaRepository<Coupon, Long> getRepository() {
		return couponRepository;
	}

	@Override
	public Coupon generate(Rule rule) {
		Coupon generatedCoupon = Coupon.builder().code(UUID.randomUUID().toString()).discountPercent(
				rule.getDiscountPercent()).discountAmount(rule.getDiscountAmount()).generatedAt(new Date()).build();
		couponRepository.save(generatedCoupon);
		return generatedCoupon;
	}
}
