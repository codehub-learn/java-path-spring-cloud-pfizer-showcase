package gr.codelearn.spring.cloud.showcase.customer.service;

import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.customer.domain.Customer;

public interface CustomerService extends BaseService<Customer, Long> {
	Customer findByEmail(String email);
}
