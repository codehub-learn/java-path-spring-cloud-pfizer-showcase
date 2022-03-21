package gr.codelearn.spring.cloud.showcase.loyalty.service;

import gr.codelearn.spring.cloud.showcase.core.base.AbstractLogComponent;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.Coupon;
import gr.codelearn.spring.cloud.showcase.loyalty.service.rule.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoyaltyServiceImpl extends AbstractLogComponent implements LoyaltyService {
	private final CouponService couponService;
	private final List<Rule<OrderResource>> ruleList;

	@PostConstruct
	public void initialize() {
		ruleList.sort(Comparator.comparingLong(Rule::getPriority));
		Collections.reverse(ruleList);

		logger.trace("Rule list hosting {} rule(s) is now sorted by highest rule priority (bigger is higher).",
					 ruleList.size());
	}

	@Override
	public Optional<Coupon> apply(OrderResource order) {
		var matchingRule = checkRules(order);

		Coupon generatedCoupon = matchingRule != null ? couponService.generate(matchingRule) : null;

		return Optional.ofNullable(generatedCoupon);
	}

	@Override
	public void declare(Coupon coupon) {
		coupon.setUsedAt(new Date());
		couponService.update(coupon);
	}

	private Rule<OrderResource> checkRules(final OrderResource order) {
		for (Rule<OrderResource> rule : ruleList) {
			if (rule.matches(order)) {
				logger.debug("Rule {} matched order[{}] giving {}% discount and {} as fixed discount.",
							 rule.getClass().getSimpleName(), order.getId(), rule.getDiscountPercent() * 100,
							 rule.getDiscountAmount());
				return rule;
			}
		}

		logger.debug("{}", order);
		return null;
	}
}
