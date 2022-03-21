package gr.codelearn.spring.cloud.showcase.catalog.service;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Category;
import gr.codelearn.spring.cloud.showcase.catalog.repository.CategoryRepository;
import gr.codelearn.spring.cloud.showcase.core.service.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "catalog", keyGenerator = "customCacheKeyGenerator")
public class CategoryServiceImpl extends BaseServiceImpl<Category> implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public JpaRepository<Category, Long> getRepository() {
		return categoryRepository;
	}

	@Override
	@Cacheable
	public Category findById(Long id) {
		logger.trace("Retrieving entity with id {}.", id);
		return getRepository().findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	@Cacheable
	public List<Category> findAll() {
		logger.trace("Retrieving all entities.");
		return categoryRepository.findAll();
	}
}
