package gr.codelearn.spring.cloud.showcase.mail.service;

import gr.codelearn.spring.cloud.showcase.mail.domain.Customer;
import gr.codelearn.spring.cloud.showcase.mail.domain.Order;
import gr.codelearn.spring.cloud.showcase.mail.domain.PaymentMethod;
import gr.codelearn.spring.cloud.showcase.mail.domain.Product;

public interface OrderService extends BaseService<Order, Long> {
	Order initiateOrder(Customer customer);

	void addItem(Order order, Product product, int quantity);

	void updateItem(Order order, Product product, int quantity);

	void deleteItem(Order order, Product product);

	Order checkout(Order order, PaymentMethod paymentMethod);

	Order findLazy(Long id);
}
