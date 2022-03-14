package gr.codelearn.spring.cloud.showcase.mail.service;

import gr.codelearn.spring.cloud.showcase.mail.domain.Customer;
import gr.codelearn.spring.cloud.showcase.mail.transfer.KeyValue;
import gr.codelearn.spring.cloud.showcase.mail.transfer.PurchasesPerCustomerCategoryDto;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {
	Customer findByEmail(String email);

	List<KeyValue<String, BigDecimal>> findCustomersPurchasedMostExpensiveProduct();

	List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory();
}
