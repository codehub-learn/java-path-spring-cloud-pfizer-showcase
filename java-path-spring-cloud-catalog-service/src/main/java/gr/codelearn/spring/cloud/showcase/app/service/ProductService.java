package gr.codelearn.spring.cloud.showcase.app.service;

import gr.codelearn.spring.cloud.showcase.app.domain.Product;
import gr.codelearn.spring.cloud.showcase.app.transfer.KeyValue;

import java.util.List;

public interface ProductService extends BaseService<Product, Long> {
	Product findBySerial(String serial);

	List<KeyValue<String, Integer>> findProductSaleFrequency();
}
