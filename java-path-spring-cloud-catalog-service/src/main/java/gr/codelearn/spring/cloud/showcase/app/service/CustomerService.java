package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Customer;
import gr.codelearn.spring.cloud.showcase.app.transfer.KeyValue;
import gr.codelearn.spring.cloud.showcase.app.transfer.PurchasesPerCustomerCategoryDto;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {
	Customer findByEmail(String email);

	List<KeyValue<String, BigDecimal>> findCustomersPurchasedMostExpensiveProduct();

	List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory();
}
