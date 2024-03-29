package gr.codelearn.spring.cloud.showcase.catalog.service;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Product;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;

public interface ProductService extends BaseService<Product, Long> {
	Product create(final Product product, Long categoryId);

	Product findBySerial(String serial);
}
