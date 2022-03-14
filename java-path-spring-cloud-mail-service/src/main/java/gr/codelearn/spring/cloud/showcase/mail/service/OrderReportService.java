package gr.codelearn.spring.cloud.showcase.mail.service;

import gr.codelearn.spring.cloud.showcase.mail.transfer.KeyValue;

import java.math.BigDecimal;
import java.util.List;

public interface OrderReportService {
	List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer();

	Long countByCustomer(String email);

	Long countByCouponCodeIsNotNullAndCustomer(String email);
}
