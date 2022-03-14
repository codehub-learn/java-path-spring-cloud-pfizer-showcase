package gr.codelearn.spring.cloud.showcase.mail.service;

import gr.codelearn.spring.cloud.showcase.mail.domain.Product;
import gr.codelearn.spring.cloud.showcase.mail.transfer.KeyValue;

import java.util.List;

public interface ProductService extends BaseService<Product, Long> {
	Product findBySerial(String serial);

	List<KeyValue<String, Integer>> findProductSaleFrequency();
}
