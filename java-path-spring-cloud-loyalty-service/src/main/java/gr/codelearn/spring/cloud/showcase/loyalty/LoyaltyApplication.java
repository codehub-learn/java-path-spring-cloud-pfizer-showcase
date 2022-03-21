package gr.codelearn.spring.cloud.showcase.loyalty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"gr.codelearn.spring.cloud.showcase.core", "gr.codelearn.spring.cloud.showcase.loyalty"})
public class LoyaltyApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoyaltyApplication.class, args);
	}
}
