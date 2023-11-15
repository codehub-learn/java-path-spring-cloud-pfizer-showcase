package gr.codelearn.spring.cloud.showcase.catalog.service;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Category;
import gr.codelearn.spring.cloud.showcase.catalog.domain.Product;
import gr.codelearn.spring.cloud.showcase.catalog.repository.ProductRepository;
import gr.codelearn.spring.cloud.showcase.core.service.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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
	public Product findBySerial(String serial) {
		return productRepository.findBySerial(serial);
	}
}
