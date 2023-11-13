package gr.codelearn.spring.showcase.app.service;

import gr.codelearn.spring.showcase.app.domain.Category;
import gr.codelearn.spring.showcase.app.domain.Product;
import gr.codelearn.spring.showcase.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"products"}, keyGenerator = "customCacheKeyGenerator")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	private final ProductRepository productRepository;
	private final CategoryService categoryService;

	@Override
	public JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
				   rollbackFor = Exception.class)
	public Product create(final Product product, Long categoryId) {
		Category category = categoryService.getReference(categoryId);
		logger.trace("Creating {} assigned to {}.", product, category);
		product.setCategory(category);
		return getRepository().save(product);
	}

	@Override
	@Cacheable
	public List<Product> findAll() {
		logger.info("List does not exist in cache, fetching from repository.");
		return productRepository.findAll();
	}

	@Override
	@Cacheable
	public Product findBySerial(String serial) {
		return productRepository.findBySerial(serial);
	}

	@CacheEvict(value = "products", allEntries = true)
	@Scheduled(cron = "0 0/5 * * * ?")
	public void clearAllCaches() {
		logger.info("Clearing products cache.");
	}
}
