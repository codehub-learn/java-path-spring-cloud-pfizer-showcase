package gr.codelearn.spring.cloud.showcase.catalog.service;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Category;
import gr.codelearn.spring.cloud.showcase.catalog.repository.CategoryRepository;
import gr.codelearn.spring.cloud.showcase.core.service.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	@Override
	public Category getReference(final Long id) {
		return categoryRepository.getReferenceById(id);
	}

	@Override
	public Category findByDescription(String description) {
		return categoryRepository.findByDescription(description);
	}
}
