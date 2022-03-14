package gr.codelearn.spring.cloud.showcase.app.bootstrap;

import gr.codelearn.spring.cloud.showcase.app.base.AbstractLogComponent;
import gr.codelearn.spring.cloud.showcase.app.domain.Order;
import gr.codelearn.spring.cloud.showcase.app.service.CustomerService;
import gr.codelearn.spring.cloud.showcase.app.service.OrderReportService;
import gr.codelearn.spring.cloud.showcase.app.service.OrderService;
import gr.codelearn.spring.cloud.showcase.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("sample-query")
@Component
@RequiredArgsConstructor
public class SampleQueryRunner extends AbstractLogComponent implements CommandLineRunner {
	private final ProductService productService;
	private final CustomerService customerService;
	private final OrderService orderService;
	private final OrderReportService orderReportService;

	@Override
	public void run(String... args) {
		logger.info("Getting first order");
		Order order = orderService.findLazy(1L);
		logger.info("{} with {} items.", order, order.getOrderItems().size());

		logger.info("------------------------------------------------------");
		logger.info("Getting average order cost per customer");
		orderReportService.findAverageOrderCostPerCustomer().forEach(
				o -> logger.info("{} with an average order cost of {}.", o.getKey(), o.getValue()));

		logger.info("------------------------------------------------------");
		logger.info("Getting average order cost per customer");
		customerService.findCustomersPurchasedMostExpensiveProduct().forEach(
				c -> logger.info("{} made {} purchases.", c.getKey(), c.getValue()));

		logger.info("------------------------------------------------------");
		logger.info("Getting average order cost per customer");
		customerService.findTotalNumberAndCostOfPurchasesPerCustomerCategory().forEach(
				c -> logger.info("{} purchases costing {} in customer category {}.", c.getPurchases(), c.getCost(),
								 c.getCategory()));

		logger.info("------------------------------------------------------");
		logger.info("Getting products and their sale frequency");
		productService.findProductSaleFrequency().forEach(
				p -> logger.info("{} sold {} times.", p.getKey(), p.getValue()));
	}
}
