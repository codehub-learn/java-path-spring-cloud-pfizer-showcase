package gr.codelearn.spring.cloud.showcase.app.service.rule;

import gr.codelearn.spring.cloud.showcase.app.domain.Order;
import gr.codelearn.spring.cloud.showcase.app.service.OrderReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MoreThanOneOrderWithoutCouponRule implements Rule<Order> {
	private final OrderReportService orderReportService;

	@Override
	public Float getDiscountPercent() {
		return 0.2F;
	}

	@Override
	public boolean matches(Order order) {
		Long orderCount = orderReportService.countByCustomer(order.getCustomer().getEmail());
		Long orderWithCoupon = orderReportService.countByCouponCodeIsNotNullAndCustomer(order.getCustomer().getEmail());

		return orderCount > 1 && orderWithCoupon == 0;
	}

	@Override
	public Long getPriority() {
		return 100L;
	}
}
