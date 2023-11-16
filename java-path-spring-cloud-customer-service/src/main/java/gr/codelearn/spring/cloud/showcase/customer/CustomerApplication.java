package gr.codelearn.spring.cloud.showcase.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
		basePackages = {"gr.codelearn.spring.cloud.showcase.core", "gr.codelearn.spring.cloud.showcase.customer"})
@EnableDiscoveryClient
public class CustomerApplication {
	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}
