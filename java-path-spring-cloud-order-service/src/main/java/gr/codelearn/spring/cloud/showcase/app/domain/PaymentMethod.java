package gr.codelearn.spring.cloud.showcase.app.domain;

/**
 * This enumeration class represents all payment methods.
 */
public enum PaymentMethod {
	WIRE_TRANSFER(0.10f), CREDIT_CARD(0.15f);
	private final float discount;

	PaymentMethod(float discount) {
		this.discount = discount;
	}

	public float getDiscount() {
		return this.discount;
	}
}
