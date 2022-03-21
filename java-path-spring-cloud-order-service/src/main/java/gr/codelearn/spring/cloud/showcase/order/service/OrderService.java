package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.core.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.ProductResource;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;

public interface OrderService extends BaseService<Order, Long> {
	Order initiateOrder(CustomerResource customer);

	void addItem(Order order, ProductResource product, int quantity);

	void updateItem(Order order, ProductResource product, int quantity);

	void deleteItem(Order order, ProductResource product);

	Order checkout(Order order, PaymentMethod paymentMethod);

	Order findLazy(Long id);
}
