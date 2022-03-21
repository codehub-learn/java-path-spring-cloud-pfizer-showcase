package gr.codelearn.spring.cloud.showcase.catalog.repository;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Product findBySerial(String serial);
}
