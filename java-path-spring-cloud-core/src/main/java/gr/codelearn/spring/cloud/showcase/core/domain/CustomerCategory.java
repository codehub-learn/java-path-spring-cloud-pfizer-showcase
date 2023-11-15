package gr.codelearn.spring.cloud.showcase.core.domain;

import lombok.Getter;

@Getter
public enum CustomerCategory {
	INDIVIDUAL(0), BUSINESS(0.2f), GOVERNMENT(0.5f);

	private final float discount;

	CustomerCategory(float discount) {
		this.discount = discount;
	}

}
