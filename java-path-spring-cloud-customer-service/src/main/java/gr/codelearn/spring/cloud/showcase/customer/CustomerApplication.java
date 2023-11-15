package gr.codelearn.spring.cloud.showcase.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {"gr.codelearn.spring.cloud.showcase.core", "gr.codelearn.spring.cloud.showcase.customer"})
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
