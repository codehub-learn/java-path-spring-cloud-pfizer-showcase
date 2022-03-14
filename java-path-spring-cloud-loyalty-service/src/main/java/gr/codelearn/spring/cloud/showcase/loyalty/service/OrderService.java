package gr.codelearn.spring.cloud.showcase.loyalty.service;

import gr.codelearn.spring.cloud.showcase.loyalty.domain.Customer;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.Order;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.Product;

public interface OrderService extends BaseService<Order, Long> {
	Order initiateOrder(Customer customer);

	void addItem(Order order, Product product, int quantity);

	void updateItem(Order order, Product product, int quantity);

	void deleteItem(Order order, Product product);

	Order checkout(Order order, PaymentMethod paymentMethod);

	Order findLazy(Long id);
}
