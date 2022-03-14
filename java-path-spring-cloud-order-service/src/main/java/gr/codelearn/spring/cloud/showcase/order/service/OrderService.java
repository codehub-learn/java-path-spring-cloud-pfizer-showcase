package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.order.domain.Customer;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;
import gr.codelearn.spring.cloud.showcase.order.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.order.domain.Product;

public interface OrderService extends BaseService<Order, Long> {
	Order initiateOrder(Customer customer);

	void addItem(Order order, Product product, int quantity);

	void updateItem(Order order, Product product, int quantity);

	void deleteItem(Order order, Product product);

	Order checkout(Order order, PaymentMethod paymentMethod);

	Order findLazy(Long id);
}
