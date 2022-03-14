package gr.codelearn.spring.cloud.showcase.catalog.bootstrap;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Category;
import gr.codelearn.spring.cloud.showcase.catalog.domain.Product;
import gr.codelearn.spring.cloud.showcase.catalog.service.CategoryService;
import gr.codelearn.spring.cloud.showcase.catalog.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Profile("generate-product-catalog")
@Component
@RequiredArgsConstructor
public class ProductCatalogRunner implements CommandLineRunner {
	private final CategoryService categoryService;
	private final ProductService productService;

	@Override
	public void run(String... args) {
		Category newCategory = Category.builder().description("Mobile Phones").build();
		//@formatter:off
		List<Product> products = List.of(
			Product.builder().serial("SN1000-0001").name("Apple iPhone 12 Pro 5G 512GB")
					.price(BigDecimal.valueOf(1629)).category(newCategory).build(),
			Product.builder().serial("SN1000-0002").name("Apple iPhone 12 Pro Max 5G 512GB")
					.price(BigDecimal.valueOf(1749)).category(newCategory).build(),
			Product.builder().serial("SN1100-0001").name("Samsung Galaxy S21 Ultra")
					.price(BigDecimal.valueOf(1479.99)).category(newCategory).build(),
			Product.builder().serial("SN1100-0002").name("Samsung Galaxy S20 Ultra")
					.price(BigDecimal.valueOf(1199)).category(newCategory).build(),
			Product.builder().serial("SN1200-0001").name("Huawei P40 Pro")
					.price(BigDecimal.valueOf(899.49)).category(newCategory).build(),
			Product.builder().serial("SN1300-0001").name("Xiaomi Redmi 9A")
					.price(BigDecimal.valueOf(199.75)).category(newCategory).build(),
			Product.builder().serial("SN1400-0001").name("RealMe C11")
					.price(BigDecimal.valueOf(129)).category(newCategory).build(),
			Product.builder().serial("SN1500-0001").name("Honor 10 Lite")
					.price(BigDecimal.valueOf(179)).category(newCategory).build(),
			Product.builder().serial("SN1000-0003").name("Apple iPhone 12 Pro Max 5G 128GB")
					.price(BigDecimal.valueOf(1339)).category(newCategory).build(),
			Product.builder().serial("SN1000-0004").name("Apple iPhone 11 Pro 256GB")
					.price(BigDecimal.valueOf(1299.99)).category(newCategory).build()
		);
		//@formatter:on

		categoryService.save(newCategory);
		productService.saveAll(products);
	}
}
