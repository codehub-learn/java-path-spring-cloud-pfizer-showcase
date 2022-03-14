package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.order.domain.Customer;
import gr.codelearn.spring.cloud.showcase.order.repository.CustomerRepository;
import gr.codelearn.spring.cloud.showcase.order.transfer.KeyValue;
import gr.codelearn.spring.cloud.showcase.order.transfer.PurchasesPerCustomerCategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
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
	public List<KeyValue<String, BigDecimal>> findCustomersPurchasedMostExpensiveProduct() {
		return customerRepository.findCustomersPurchasedMostExpensiveProduct();
	}

	@Override
	public List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory() {
		return customerRepository.findTotalNumberAndCostOfPurchasesPerCustomerCategory();
	}
}
