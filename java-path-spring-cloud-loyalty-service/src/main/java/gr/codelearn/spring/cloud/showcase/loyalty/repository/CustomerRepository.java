package gr.codelearn.spring.cloud.showcase.loyalty.repository;

import gr.codelearn.spring.cloud.showcase.loyalty.domain.Customer;
import gr.codelearn.spring.cloud.showcase.loyalty.transfer.KeyValue;
import gr.codelearn.spring.cloud.showcase.loyalty.transfer.PurchasesPerCustomerCategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);

	@Query(name = "Customer.mostExpensiveProductPurchases", nativeQuery = true)
	List<KeyValue<String, BigDecimal>> findCustomersPurchasedMostExpensiveProduct();

	@Query(nativeQuery = true)
	List<PurchasesPerCustomerCategoryDto> findTotalNumberAndCostOfPurchasesPerCustomerCategory();
}
