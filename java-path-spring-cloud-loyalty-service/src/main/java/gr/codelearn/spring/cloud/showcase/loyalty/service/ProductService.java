package gr.codelearn.spring.cloud.showcase.loyalty.service;

import gr.codelearn.spring.cloud.showcase.loyalty.domain.Product;
import gr.codelearn.spring.cloud.showcase.loyalty.transfer.KeyValue;

import java.util.List;

public interface ProductService extends BaseService<Product, Long> {
	Product findBySerial(String serial);

	List<KeyValue<String, Integer>> findProductSaleFrequency();
}
