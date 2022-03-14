package gr.codelearn.spring.cloud.showcase.order.repository;

import gr.codelearn.spring.cloud.showcase.order.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
