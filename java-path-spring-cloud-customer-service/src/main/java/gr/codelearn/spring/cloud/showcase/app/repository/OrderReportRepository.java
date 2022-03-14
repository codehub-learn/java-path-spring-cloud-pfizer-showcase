package gr.codelearn.spring.cloud.showcase.app.repository;

import gr.codelearn.spring.cloud.showcase.app.domain.Order;
import gr.codelearn.spring.cloud.showcase.app.transfer.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderReportRepository extends JpaRepository<Order, Long> {
	@Query("select new gr.codelearn.spring.cloud.showcase.app.transfer.KeyValue(concat(c.firstname, '.', c.lastname)," +
			" avg(o.cost)) from Order o join o.customer c group by c order by c.lastname, c.firstname")
	List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer();

	@Query("select count(o) from Order o inner join Customer c on o.customer = c.id where c.email = ?1")
	Long countByCustomer(String email);

	@Query("select count(o) from Order o inner join Customer c on o.customer = c.id where c.email = ?1 and o" +
			".couponCode is not null")
	Long countByCouponCodeIsNotNullAndCustomer(String email);
}
