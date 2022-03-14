package gr.codelearn.spring.cloud.showcase.app.repository;

import gr.codelearn.spring.cloud.showcase.app.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
