package gr.codelearn.spring.cloud.showcase.core.domain;

import lombok.Getter;

@Getter
public enum PaymentMethod {
	WIRE_TRANSFER(0.10f), CREDIT_CARD(0.15f);
	private final float discount;

	PaymentMethod(float discount) {
		this.discount = discount;
	}
}
