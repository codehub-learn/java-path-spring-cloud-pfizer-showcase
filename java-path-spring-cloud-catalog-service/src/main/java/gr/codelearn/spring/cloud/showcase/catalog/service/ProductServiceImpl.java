package gr.codelearn.spring.cloud.showcase.catalog.service;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Product;
import gr.codelearn.spring.cloud.showcase.catalog.repository.ProductRepository;
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
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {
	private final ProductRepository productRepository;

	@Override
	public JpaRepository<Product, Long> getRepository() {
		return productRepository;
	}

	@Override
	@Cacheable
	public Product findById(Long id) {
		logger.trace("Retrieving entity with id {}.", id);
		return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	@Cacheable
	public List<Product> findAll() {
		logger.trace("Retrieving all entities.");
		return productRepository.findAll();
	}

	@Override
	@Cacheable
	public Product findBySerial(String serial) {
		return productRepository.findBySerial(serial);
	}
}
