package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.core.transfer.KeyValue;
import gr.codelearn.spring.cloud.showcase.order.transfer.PurchasesPerCustomerCategoryDto;

import java.math.BigDecimal;
import java.util.List;

public interface OrderReportService {
	List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer();

	Long countByCustomer(String email);

	List<KeyValue<String, Integer>> findProductSaleFrequency();

	List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory();
}
