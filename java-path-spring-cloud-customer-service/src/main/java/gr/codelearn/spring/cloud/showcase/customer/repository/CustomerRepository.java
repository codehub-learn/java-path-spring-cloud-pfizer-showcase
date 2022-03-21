package gr.codelearn.spring.cloud.showcase.customer.repository;

import gr.codelearn.spring.cloud.showcase.customer.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);
}
