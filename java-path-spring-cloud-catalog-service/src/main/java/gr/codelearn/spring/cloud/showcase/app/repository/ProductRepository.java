package gr.codelearn.spring.cloud.showcase.app.repository;

import gr.codelearn.spring.cloud.showcase.app.domain.Product;
import gr.codelearn.spring.cloud.showcase.app.transfer.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findBySerial(String serial);

	@Query()
	List<KeyValue<String, Integer>> findProductSaleFrequency();
}
