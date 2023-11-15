package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Category;
import gr.codelearn.spring.cloud.showcase.app.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"categories"}, keyGenerator = "customCacheKeyGenerator")
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
	@Cacheable
	public List<Category> findAll() {
		logger.info("Categories do not exist in cache, fetching from repository.");
		return categoryRepository.findAll();
	}

	@Override
	@Cacheable(key = "#id")
	public Category get(Long id) {
		logger.info("Category does not exist in cache, fetching from repository.");
		return categoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	@Cacheable
	public Category findByDescription(String description) {
		logger.info("Category does not exist in cache, fetching from repository.");
		return categoryRepository.findByDescription(description);
	}

	@Caching(evict = {@CacheEvict(value = "categories", allEntries = true)})
	@Scheduled(cron = "0 0/5 * * * ?")
	public void clearAllCaches() {
		logger.info("Clearing categories cache.");
	}
}
