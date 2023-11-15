package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Customer;
import gr.codelearn.spring.cloud.showcase.app.transfer.KeyValue;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService extends BaseService<Customer, Long> {
	Customer findByEmail(String email);

	List<KeyValue<String, Long>> findCustomersPurchasedMostExpensiveProduct();

	List<KeyValue<String, BigDecimal>> getStatistics();
}
