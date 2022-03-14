package gr.codelearn.spring.cloud.showcase.order.service.rule;

import gr.codelearn.spring.cloud.showcase.order.domain.BaseEntity;

import java.math.BigDecimal;

public interface Rule<T extends BaseEntity> {
	default Float getDiscountPercent() {
		return 0F;
	}

	default BigDecimal getDiscountAmount() {
		return BigDecimal.ZERO;
	}

	default boolean matches(T target) {
		return false;
	}

	default Long getPriority() {
		return 1L;
	}
}
