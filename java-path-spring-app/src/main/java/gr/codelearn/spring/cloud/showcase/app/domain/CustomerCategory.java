package gr.codelearn.spring.cloud.showcase.app.domain;

public enum CustomerCategory {
	INDIVIDUAL(0), BUSINESS(0.2f), GOVERNMENT(0.5f);

	private final float discount;

	CustomerCategory(float discount) {
		this.discount = discount;
	}

	public float getDiscount() {
		return this.discount;
	}
}
