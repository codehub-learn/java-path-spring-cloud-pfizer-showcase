package gr.codelearn.spring.cloud.showcase.mail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(
		exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class,
				   HibernateJpaAutoConfiguration.class})
//@ComponentScan(basePackages = {"gr.codelearn.spring.cloud.showcase.core", "gr.codelearn.spring.cloud.showcase.mail"})
public class MailApplication {
	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}
}
