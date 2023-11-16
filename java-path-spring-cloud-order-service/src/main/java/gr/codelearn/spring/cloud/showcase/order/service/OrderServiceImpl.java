package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.core.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.core.service.BaseServiceImpl;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.ProductResource;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;
import gr.codelearn.spring.cloud.showcase.order.domain.OrderItem;
import gr.codelearn.spring.cloud.showcase.order.mapper.OrderMapper;
import gr.codelearn.spring.cloud.showcase.order.repository.OrderRepository;
import gr.codelearn.spring.cloud.showcase.order.service.client.MailServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseServiceImpl<Order> implements OrderService {
	private final OrderRepository orderRepository;
	private final OrderMapper orderMapper;
	private final MailServiceClient mailServiceClient;

	@Override
	public JpaRepository<Order, Long> getRepository() {
		return orderRepository;
	}

	@Override
	public Order initiateOrder(CustomerResource customer) {
		return Order.builder().email(customer.getEmail()).customerCategory(customer.getCustomerCategory()).build();
	}

	@Override
	public void addItem(Order order, ProductResource product, int quantity) {
		if (checkNullability(order, product)) {
			return;
		}

		boolean increasedQuantity = false;

		// If product is already contained in the order, don't add it again, just increase the quantity accordingly
		for (OrderItem oi : order.getOrderItems()) {
			if (oi.getSerial().equals(product.getSerial())) {
				oi.setQuantity(oi.getQuantity() + quantity);
				increasedQuantity = true;
				break;
			}
		}

		if (!increasedQuantity) {
			order.getOrderItems().add(newOrderItem(order, product, quantity));
		}

		logger.debug("ProductResource[{}] added to Order[{}]", product, order);
	}

	private boolean checkNullability(Order order, ProductResource product) {
		if (order == null) {
			logger.warn("Order is null.");
			return true;
		}
		if (product == null) {
			logger.warn("ProductResource is null.");
			return true;
		}
		return false;
	}

	private OrderItem newOrderItem(Order order, ProductResource product, int quantity) {
		//@formatter:off
		return OrderItem.builder()
						.serial(product.getSerial())
						.name(product.getName())
						.categoryDescription(product.getCategory().getDescription())
						.order(order)
						.quantity(quantity)
						.price(product.getPrice()).build();
		//@formatter:on
	}

	@Override
	public void updateItem(Order order, ProductResource product, int quantity) {
		if (checkNullability(order, product)) {
			return;
		}

		order.getOrderItems().removeIf(oi -> oi.getSerial().equals(product.getSerial()));
		order.getOrderItems().add(newOrderItem(order, product, quantity));

		logger.debug("ProductResource[{}] updated in Order[{}]", product, order);
	}

	@Override
	public void deleteItem(Order order, ProductResource product) {
		if (checkNullability(order, product)) {
			return;
		}

		order.getOrderItems().removeIf(oi -> oi.getSerial().equals(product.getSerial()));
		logger.debug("ProductResource[{}] removed from Order[{}]", product, order);
	}

	@Override
	public Order checkout(Order order, PaymentMethod paymentMethod) {
		if (!validate(order)) {
			logger.warn("Order should have customer, order items, and payment type defined before being able to be " +
						"checkout the order.");
			return null;
		}

		// Set all order fields with proper values
		order.setPaymentMethod(paymentMethod);
		order.setSubmitDate(new Date());

		order.setCost(giveDiscount(order));

		var savedOrder = create(order);
		mailServiceClient.send(savedOrder.getEmail(),
							   String.format("Successfully submitted your order %d.", savedOrder.getId()),
							   String.format(
									   "You have successfully submitted your order with id %d costing %f at "
									   + "%tc.",
									   savedOrder.getId(), savedOrder.getCost(), savedOrder.getSubmitDate()));
		return savedOrder;
	}

	private boolean validate(Order order) {
		return order != null && !order.getOrderItems().isEmpty() && order.getEmail() != null &&
			   order.getCustomerCategory() != null;
	}

	private BigDecimal giveDiscount(Order order) {
		var totalDiscountPercent = order.getCustomerCategory().getDiscount() + order.getPaymentMethod().getDiscount();

		// Calculate original order cost
		var originalCost = order.getOrderItems()
								.stream()
								.map(oi -> oi.getPrice().multiply(BigDecimal.valueOf(oi.getQuantity())))
								.reduce(BigDecimal.ZERO, BigDecimal::add);

		// Apply discounts
		var costAfterDiscount = originalCost.multiply(BigDecimal.valueOf(1F - totalDiscountPercent));

		logger.debug("Order[{}], originalCost: {}, totalDiscount: {}%, finalCost: {}.", order.getId(), originalCost,
					 totalDiscountPercent * 100, costAfterDiscount);

		return costAfterDiscount;
	}

	@Override
	public List<Order> findAll() {
		return orderRepository.findAllLazy();
	}

	@Override
	public Order findLazy(Long id) {
		return orderRepository.findLazy(id)
							  .orElseThrow(() -> new NoSuchElementException(
									  String.format("There was no order found matching id %d.", id)));
	}
}
