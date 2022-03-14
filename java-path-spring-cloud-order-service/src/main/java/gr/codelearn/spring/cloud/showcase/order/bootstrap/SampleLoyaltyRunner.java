package gr.codelearn.spring.cloud.showcase.order.bootstrap;

import gr.codelearn.spring.cloud.showcase.order.base.AbstractLogComponent;
import gr.codelearn.spring.cloud.showcase.order.domain.Customer;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;
import gr.codelearn.spring.cloud.showcase.order.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.order.service.CustomerService;
import gr.codelearn.spring.cloud.showcase.order.service.OrderService;
import gr.codelearn.spring.cloud.showcase.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("sample-loyalty")
@Component
@RequiredArgsConstructor
public class SampleLoyaltyRunner extends AbstractLogComponent implements CommandLineRunner {
	private final ProductService productService;
	private final CustomerService customerService;
	private final OrderService orderService;

	@Override
	public void run(String... args) {
		Customer customer = customerService.findByEmail("malcolm.paker@gmail.com");
		Order order = orderService.initiateOrder(customer);

		orderService.addItem(order, productService.findBySerial("SN1000-0002"), 1);
		orderService.addItem(order, productService.findBySerial("SN1200-0001"), 1);
		orderService.addItem(order, productService.findBySerial("SN1000-0001"), 3);

		orderService.checkout(order, PaymentMethod.CREDIT_CARD);
	}
}
