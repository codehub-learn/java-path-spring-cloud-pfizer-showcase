package gr.codelearn.spring.cloud.showcase.catalog.transfer;

import java.math.BigDecimal;

public interface PurchasesPerCustomerCategoryDto {
	String getCategory();

	Long getPurchases();

	BigDecimal getCost();
}
