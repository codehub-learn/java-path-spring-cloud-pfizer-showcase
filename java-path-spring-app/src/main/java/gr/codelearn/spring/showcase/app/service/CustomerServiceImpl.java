package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Customer;
import gr.codelearn.spring.showcase.app.domain.CustomerCategory;
import gr.codelearn.spring.showcase.app.repository.CustomerRepository;
import gr.codelearn.spring.showcase.app.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements CustomerService {
	private final CustomerRepository customerRepository;

	@Override
	public JpaRepository<Customer, Long> getRepository() {
		return customerRepository;
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public List<KeyValue<String, Long>> findCustomersPurchasedMostExpensiveProduct() {
		return customerRepository.findCustomersPurchasedMostExpensiveProduct();
	}

	@Override
	public List<KeyValue<String, BigDecimal>> getStatistics() {
		return List.of(new KeyValue<String, BigDecimal>("customerSize",
														BigDecimal.valueOf(customerRepository.count())),
					   new KeyValue<String, BigDecimal>("mostExpensiveCustomersSize", BigDecimal.valueOf(
							   customerRepository.findCustomersPurchasedMostExpensiveProduct().size())),
					   new KeyValue<String, BigDecimal>("customerCategorySize",
														BigDecimal.valueOf(CustomerCategory.values().length)));
	}
}
