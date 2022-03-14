package gr.codelearn.spring.cloud.showcase.order.service;

import gr.codelearn.spring.cloud.showcase.order.repository.OrderReportRepository;
import gr.codelearn.spring.cloud.showcase.order.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderReportServiceImpl implements OrderReportService {
	private final OrderReportRepository orderReportRepository;

	@Override
	public List<KeyValue<String, BigDecimal>> findAverageOrderCostPerCustomer() {
		return orderReportRepository.findAverageOrderCostPerCustomer();
	}

	@Override
	public Long countByCustomer(final String email) {
		return orderReportRepository.countByCustomer(email);
	}

	@Override
	public Long countByCouponCodeIsNotNullAndCustomer(final String email) {
		return orderReportRepository.countByCouponCodeIsNotNullAndCustomer(email);
	}
}
