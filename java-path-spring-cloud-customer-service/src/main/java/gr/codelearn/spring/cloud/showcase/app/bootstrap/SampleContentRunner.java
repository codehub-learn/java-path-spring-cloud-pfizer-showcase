package gr.codelearn.spring.cloud.showcase.app.bootstrap;

import gr.codelearn.spring.cloud.showcase.app.base.AbstractLogComponent;
import gr.codelearn.spring.cloud.showcase.app.domain.Customer;
import gr.codelearn.spring.cloud.showcase.app.domain.CustomerCategory;
import gr.codelearn.spring.cloud.showcase.app.domain.Order;
import gr.codelearn.spring.cloud.showcase.app.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.app.service.CategoryService;
import gr.codelearn.spring.cloud.showcase.app.service.CustomerService;
import gr.codelearn.spring.cloud.showcase.app.service.OrderService;
import gr.codelearn.spring.cloud.showcase.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("sample-content")
@Component
@RequiredArgsConstructor
public class SampleContentRunner extends AbstractLogComponent implements CommandLineRunner {
	private final CategoryService categoryService;
	private final ProductService productService;
	private final CustomerService customerService;
	private final OrderService orderService;

	@Override
	public void run(String... args) {
		//@formatter:off
		try{
			List<Customer> customers = List.of(
				Customer.builder().email("john.porter@gmail.com")
						.firstname("John").lastname("Porter")
						.address("2955 Blackwell Street")
						.customerCategory(CustomerCategory.BUSINESS).age(40).build(),
				Customer.builder().email("malcolm.paker@gmail.com")
						.firstname("Malcolm").lastname("Parker")
						.address("4071 Kelly Drive")
						.customerCategory(CustomerCategory.GOVERNMENT).age(32).build(),
				Customer.builder().email("terry.jones@gmail.com")
						.firstname("Terry").lastname("Jones")
						.address("3849 Hinkle Lake Road")
						.customerCategory(CustomerCategory.BUSINESS).age(57).build(),
				Customer.builder().email("peter.mercury@outlook.com")
						.firstname("Peter").lastname("Mercury")
						.address("Freddie Street 28th")
						.customerCategory(CustomerCategory.INDIVIDUAL).age(32).build());

			logger.debug("Saving some customers");
			customerService.saveAll(customers);
		} catch (DataIntegrityViolationException dive) {
			logger.warn("Unable to persist sample customer list as they probably already exist!");
		} catch (DataAccessException dae) {
			logger.error("Error occurred while interacting with underlying database, see the details", dae);
		}
		//@formatter:on

		logger.debug("Reporting all categories");
		categoryService.findAll().forEach(i -> logger.debug("{}", i));

		logger.debug("Reporting all products");
		productService.findAll().forEach(i -> logger.debug("{}", i));

		logger.debug("Reporting all customers");
		customerService.findAll().forEach(i -> logger.debug("{}", i));

		// We don't mind if a "find" method returns a null
		logger.info("Does customer exist? {}.", (customerService.findByEmail("peter.mercury@outlook.com") != null));
		logger.info("Does customer exist?: {}.", (customerService.findByEmail("non-existing@gmail.com") != null));

		////////////////////////////////////////////////////////////////////////////
		// Load customer and create an order by adding/updating/removing content before checking it out
		Customer firstCustomer = customerService.findByEmail("john.porter@gmail.com");
		Order firstOrder = orderService.initiateOrder(firstCustomer);

		// Add item(s) both existing and non-existing
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 1);
		orderService.addItem(firstOrder, productService.findBySerial("SN1300-0001"), 3);
		// Add item(s) both existing and non-existing
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 2);
		orderService.addItem(firstOrder, productService.findBySerial("SN1100-0001"), 1);
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0004"), 1);
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0008"), 1);
		// Update item(s)
		orderService.addItem(firstOrder, productService.findBySerial("SN1000-0001"), 1);
		orderService.updateItem(firstOrder, productService.findBySerial("SN1000-0004"), 2);
		// Remove item(s)
		orderService.deleteItem(firstOrder, productService.findBySerial("SN1100-0001"));
		// Add some more item(s)
		orderService.addItem(firstOrder, productService.findBySerial("SN1300-0001"), 2);
		// Checkout order
		orderService.checkout(firstOrder, PaymentMethod.CREDIT_CARD);

		// Second customer and order
		Customer secondCustomer = customerService.findByEmail("peter.mercury@outlook.com");
		Order secondOrder = orderService.initiateOrder(secondCustomer);
		// Add item(s) to second order
		orderService.addItem(secondOrder, productService.findBySerial("SN1000-0002"), 1);
		orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
		orderService.addItem(secondOrder, productService.findBySerial("SN1200-0001"), 1);
		orderService.addItem(secondOrder, productService.findBySerial("SN1299-0001"), 1);
		// Checkout 2nd order
		orderService.checkout(secondOrder, PaymentMethod.WIRE_TRANSFER);

		// Third customer and order
		Customer thirdCustomer = customerService.findByEmail("malcolm.paker@gmail.com");
		Order thirdOrder = orderService.initiateOrder(thirdCustomer);
		orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0001"), 3);
		orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0002"), 2);
		orderService.addItem(thirdOrder, productService.findBySerial("SN1000-0003"), 1);
		// Checkout 3rd order
		orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD);

		// Fourth customer and order
		Customer fourthCustomer = customerService.findByEmail("terry.jones@gmail.com");
		Order fourthOrder = orderService.initiateOrder(fourthCustomer);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1300-0001"), 1);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1400-0001"), 2);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1500-0001"), 1);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0003"), 1);
		orderService.addItem(fourthOrder, productService.findBySerial("SN1000-0004"), 1);
		// Checkout 4th order
		orderService.checkout(fourthOrder, PaymentMethod.CREDIT_CARD);

		logger.debug("Reporting all orders");
		orderService.findAll().forEach(order -> {
			logger.debug("{} with {} items.", order, order.getOrderItems().size());
		});
	}
}
