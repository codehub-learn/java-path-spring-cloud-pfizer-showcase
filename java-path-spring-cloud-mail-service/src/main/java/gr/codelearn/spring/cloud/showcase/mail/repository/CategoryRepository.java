package gr.codelearn.spring.cloud.showcase.mail.repository;

import gr.codelearn.spring.cloud.showcase.mail.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
