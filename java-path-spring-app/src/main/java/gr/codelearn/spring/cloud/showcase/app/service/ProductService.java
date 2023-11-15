package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Product;

public interface ProductService extends BaseService<Product, Long> {
	Product create(final Product product, Long categoryId);

	Product findBySerial(String serial);
}
