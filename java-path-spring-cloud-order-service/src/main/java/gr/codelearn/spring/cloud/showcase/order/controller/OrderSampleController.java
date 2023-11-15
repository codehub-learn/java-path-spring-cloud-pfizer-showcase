package gr.codelearn.spring.cloud.showcase.order.controller;

import gr.codelearn.spring.cloud.showcase.core.base.BaseComponent;
import gr.codelearn.spring.cloud.showcase.core.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.ProductResource;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;
import gr.codelearn.spring.cloud.showcase.order.service.OrderReportService;
import gr.codelearn.spring.cloud.showcase.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders-sample")
public class OrderSampleController extends BaseComponent {
	private final OrderService orderService;
	private final OrderReportService orderReportService;
	//	private final CustomerServiceClient customerServiceClient;
	//	private final CatalogServiceClient catalogServiceClient;

	@GetMapping(params = "mode=single")
	public void generateSingleOrder() {
		// Load customer and create an order by adding/updating/removing content before checking it out
		CustomerResource c1 = getCustomerResource("c.giannacoulis@codehub.gr");
		Order singleOrder = orderService.initiateOrder(c1);

		// Add item(s) both existing and non-existing
		orderService.addItem(singleOrder, getProductResource("SN1000-0001"), 5);
		orderService.checkout(singleOrder, PaymentMethod.CREDIT_CARD);
	}

	@GetMapping(params = "mode=multiple")
	public void generateContent() {
		// Load customer and create an order by adding/updating/removing content before checking it out
		CustomerResource c1 = getCustomerResource("c.giannacoulis@codehub.gr");
		Order firstOrder = orderService.initiateOrder(c1);

		// Add item(s) both existing and non-existing
		orderService.addItem(firstOrder, getProductResource("SN1000-0001"), 2);
		orderService.addItem(firstOrder, getProductResource("SN1100-0001"), 1);
		orderService.addItem(firstOrder, getProductResource("SN1000-0004"), 1);
		orderService.addItem(firstOrder, getProductResource("SN1000-0008"), 1);
		// Update item(s)
		orderService.addItem(firstOrder, getProductResource("SN1000-0001"), 1);
		orderService.updateItem(firstOrder, getProductResource("SN1000-0004"), 2);
		// Remove item(s)
		orderService.deleteItem(firstOrder, getProductResource("SN1100-0001"));
		// Add some more item(s)
		orderService.addItem(firstOrder, getProductResource("SN1300-0001"), 2);
		// Checkout order
		orderService.checkout(firstOrder, PaymentMethod.CREDIT_CARD);

		// Second customer and order
		CustomerResource c2 = getCustomerResource(2L);
		Order secondOrder = orderService.initiateOrder(c2);
		// Add item(s) to second order
		orderService.addItem(secondOrder, getProductResource("SN1000-0002"), 1);
		orderService.addItem(secondOrder, getProductResource("SN1200-0001"), 1);
		orderService.addItem(secondOrder, getProductResource("SN1200-0001"), 1);
		orderService.addItem(secondOrder, getProductResource("SN1299-0001"), 1);
		// Checkout 2nd order
		orderService.checkout(secondOrder, PaymentMethod.WIRE_TRANSFER);

		// Third customer and order
		CustomerResource c3 = getCustomerResource("malcolm.paker@gmail.com");
		Order thirdOrder = orderService.initiateOrder(c3);
		orderService.addItem(thirdOrder, getProductResource("SN1000-0001"), 3);
		orderService.addItem(thirdOrder, getProductResource("SN1000-0002"), 2);
		orderService.addItem(thirdOrder, getProductResource("SN1000-0003"), 1);
		// Checkout 3rd order
		orderService.checkout(thirdOrder, PaymentMethod.CREDIT_CARD);

		// Fourth customer and order
		CustomerResource c4 = getCustomerResource("terry.jones@gmail.com");
		Order fourthOrder = orderService.initiateOrder(c4);
		orderService.addItem(fourthOrder, getProductResource("SN1300-0001"), 1);
		orderService.addItem(fourthOrder, getProductResource("SN1400-0001"), 2);
		orderService.addItem(fourthOrder, getProductResource("SN1500-0001"), 1);
		orderService.addItem(fourthOrder, getProductResource("SN1000-0003"), 1);
		orderService.addItem(fourthOrder, getProductResource("SN1000-0004"), 1);
		// Checkout 4th order
		orderService.checkout(fourthOrder, PaymentMethod.CREDIT_CARD);

		// Load
		logger.info("Fourth order: {}.", fourthOrder);
		orderService.deleteById(fourthOrder.getId());

		logger.debug("{}.", orderService.findLazy(1L));

		// **** SOME EXTRA ORDERS FOR SHOWCASING WITHIN THE APPLICATION **** //
		// customer and order
		CustomerResource c5 = getCustomerResource(5L);
		Order o5 = orderService.initiateOrder(c5);
		// Add item(s) to order
		orderService.addItem(o5, getProductResource("SN1000-0002"), 4);
		orderService.addItem(o5, getProductResource("SN1200-0001"), 2);
		orderService.addItem(o5, getProductResource("SN1200-0001"), 1);
		// Checkout order
		orderService.checkout(o5, PaymentMethod.WIRE_TRANSFER);

		// customer and order
		CustomerResource c6 = getCustomerResource(6L);
		Order o6 = orderService.initiateOrder(c6);
		// Add item(s) to order
		orderService.addItem(o6, getProductResource("SN1200-0001"), 2);
		// Checkout order
		orderService.checkout(o6, PaymentMethod.CREDIT_CARD);

		// customer and order
		CustomerResource c7 = getCustomerResource(7L);
		Order o7 = orderService.initiateOrder(c7);
		// Add item(s) to order
		orderService.addItem(o7, getProductResource("SN1000-0002"), 4);
		orderService.addItem(o7, getProductResource("SN1200-0001"), 1);
		// Checkout order
		orderService.checkout(o7, PaymentMethod.CREDIT_CARD);

		// customer and order
		CustomerResource c8 = getCustomerResource(8L);
		Order o8 = orderService.initiateOrder(c8);
		// Add item(s) to order
		orderService.addItem(o8, getProductResource("SN1000-0002"), 1);
		orderService.addItem(o8, getProductResource("SN1200-0001"), 1);
		orderService.addItem(o8, getProductResource("SN1200-0001"), 1);
		// Checkout order
		orderService.checkout(o8, PaymentMethod.WIRE_TRANSFER);

		// customer and order
		CustomerResource c9 = getCustomerResource(9L);
		Order o9 = orderService.initiateOrder(c9);
		// Add item(s) to order
		orderService.addItem(o9, getProductResource("SN1200-0001"), 1);
		// Checkout order
		orderService.checkout(o9, PaymentMethod.WIRE_TRANSFER);

		// customer and order
		CustomerResource c10 = getCustomerResource(10L);
		Order o10 = orderService.initiateOrder(c10);
		// Add item(s) to order
		orderService.addItem(o10, getProductResource("SN1200-0001"), 3);
		// Checkout order
		orderService.checkout(o10, PaymentMethod.CREDIT_CARD);

		// REPORTS
		logger.info("REPORT: Displaying average order cost per customer");
		orderReportService.findAverageOrderCostPerCustomer().forEach(i -> logger.info("{}", i));

		logger.info("REPORT: Displaying total number of purchases and corresponding cost per customer category");
		orderReportService.findTotalNumberAndCostOfPurchasesPerCustomerCategory().forEach(
				i -> logger.info("{} was purchased {} times costing {}.", i.getCategory(), i.getPurchases(),
								 i.getCost()));
	}

	private ProductResource getProductResource(final String s) {
		return null;//Objects.requireNonNull(catalogServiceClient.findBySerial(s).getBody()).getData();
	}

	private CustomerResource getCustomerResource(Long id) {
		return null;//Objects.requireNonNull(customerServiceClient.findById(id).getBody()).getData();
	}

	private CustomerResource getCustomerResource(final String s) {
		return null;//Objects.requireNonNull(customerServiceClient.findByEmail(s).getBody()).getData();
	}
}
