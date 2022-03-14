package gr.codelearn.spring.cloud.showcase.mail.controller;

import gr.codelearn.spring.cloud.showcase.mail.domain.Order;
import gr.codelearn.spring.cloud.showcase.mail.service.BaseService;
import gr.codelearn.spring.cloud.showcase.mail.service.OrderReportService;
import gr.codelearn.spring.cloud.showcase.mail.service.OrderService;
import gr.codelearn.spring.cloud.showcase.mail.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.mail.transfer.KeyValue;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController extends AbstractController<Order> {
	private final OrderService orderService;
	private final OrderReportService orderReportService;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@GetMapping(headers = {"action=averageOrderCostPerCustomer", "api-version"})
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> findAverageOrderCostPerCustomerV2(
			@Nullable @RequestHeader("api-version") Long apiVersion) {
		logger.info("API Version passed is {}.", apiVersion);

		final List<KeyValue<String, BigDecimal>> results = orderReportService.findAverageOrderCostPerCustomer();
		return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder().data(results).build());
	}
}
