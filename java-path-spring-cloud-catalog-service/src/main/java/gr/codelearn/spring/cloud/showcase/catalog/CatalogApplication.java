package gr.codelearn.spring.cloud.showcase.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"gr.codelearn.spring.cloud.showcase.core", "gr.codelearn.spring.cloud.showcase.catalog"})
public class CatalogApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatalogApplication.class, args);
	}
}
