package gr.codelearn.spring.cloud.showcase.customer.bootstrap;

import gr.codelearn.spring.cloud.showcase.core.base.BaseComponent;
import gr.codelearn.spring.cloud.showcase.core.domain.CustomerCategory;
import gr.codelearn.spring.cloud.showcase.customer.domain.Customer;
import gr.codelearn.spring.cloud.showcase.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("generate-customers")
@Component
@RequiredArgsConstructor
public class CustomerSampleGenerator extends BaseComponent implements CommandLineRunner {
	private final CustomerService customerService;

	@Override
	public void run(String... args) {
		try {
			//@formatter:off
			List<Customer> customers = customerService.createAll(List.of(
					Customer.builder().email("c.giannacoulis@codehub.gr")
							.firstname("Constantinos").lastname("Giannacoulis")
							.address("3583 Tennessee Avenue")
							.customerCategory(CustomerCategory.INDIVIDUAL).age(47).build(),
					Customer.builder().email("john.porter@gmailX.com")
							.firstname("John").lastname("Porter")
							.address("2955 Blackwell Street")
							.customerCategory(CustomerCategory.BUSINESS).age(40).build(),
					Customer.builder().email("malcolm.paker@gmailX.com")
							.firstname("Malcolm").lastname("Parker")
							.address("4071 Kelly Drive")
							.customerCategory(CustomerCategory.GOVERNMENT).age(32).build(),
					Customer.builder().email("terry.jones@gmailX.com")
							.firstname("Terry").lastname("Jones")
							.address("3849 Hinkle Lake Road")
							.customerCategory(CustomerCategory.BUSINESS).age(57).build(),
					Customer.builder().email("peter.mercury@outlook.com")
							.firstname("Peter").lastname("Mercury")
							.address("Freddie Street 28th")
							.customerCategory(CustomerCategory.INDIVIDUAL).age(32).build(),
					Customer.builder().email("magdalene.ferguson@gmailX.com")
							.firstname("Magdalene").lastname("Ferguson")
							.address("Jelly Avenue 73")
							.customerCategory(CustomerCategory.INDIVIDUAL).age(32).build(),
					Customer.builder().email("jones.pirves@gmailX.com")
							.firstname("Jones").lastname("Pirves")
							.address("3rd Mountain Hike, 3")
							.customerCategory(CustomerCategory.INDIVIDUAL).age(32).build(),
					Customer.builder().email("michael.anderson@gmailX.com")
							.firstname("Michael").lastname("Anderson")
							.address("Hollywood Street 63")
							.customerCategory(CustomerCategory.BUSINESS).age(32).build(),
					Customer.builder().email("yennefer.lawrance@windowsliveX.com")
							.firstname("Yennefer").lastname("Lawrance")
							.address("Rivia 43")
							.customerCategory(CustomerCategory.BUSINESS).age(32).build(),
					Customer.builder().email("mary.ferry@windowsliveX.com")
							.firstname("Mary").lastname("Ferry")
							.address("Downtown 17, California")
							.customerCategory(CustomerCategory.GOVERNMENT).age(32).build()));
			//@formatter:on

			logger.info("{} customers created.", customers.size());
		} catch (DataIntegrityViolationException dive) {
			logger.warn("Unable to persist entities, entries probably already exist!");
		} catch (DataAccessException dae) {
			logger.error("Error occurred while interacting with the database.", dae);

		}
	}
}
