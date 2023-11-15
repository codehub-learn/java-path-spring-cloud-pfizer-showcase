package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Category;

public interface CategoryService extends BaseService<Category, Long> {
	Category getReference(Long id);

	Category findByDescription(String description);
}
