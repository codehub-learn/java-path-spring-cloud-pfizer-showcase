package gr.codelearn.spring.cloud.showcase.order.controller;

import gr.codelearn.spring.cloud.showcase.core.controller.BaseController;
import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.KeyValue;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;
import gr.codelearn.spring.cloud.showcase.order.mapper.OrderMapper;
import gr.codelearn.spring.cloud.showcase.order.service.OrderReportService;
import gr.codelearn.spring.cloud.showcase.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderResourceController extends BaseController<Order, OrderResource> {
	private final OrderService orderService;
	private final OrderReportService orderReportService;
	private final OrderMapper orderMapper;

	@Override
	public BaseService<Order, Long> getBaseService() {
		return orderService;
	}

	@Override
	public BaseMapper<Order, OrderResource> getMapper() {
		return orderMapper;
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<OrderResource>> findById(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<OrderResource>builder().data(orderMapper.toResource(orderService.findLazy(id))).build());
	}

	@GetMapping(headers = {"action=countByCustomer"})
	public ResponseEntity<ApiResponse<Long>> countByCustomer(final String email) {
		return ResponseEntity.ok(ApiResponse.<Long>builder().data(orderReportService.countByCustomer(email)).build());
	}

	@GetMapping(headers = {"action=countByNotNullCouponCodeAndEmail"})
	public ResponseEntity<ApiResponse<Long>> countByNotNullCouponCodeAndEmail(final String email) {
		return ResponseEntity.ok(ApiResponse.<Long>builder().data(orderReportService.countByCustomer(email)).build());
	}

	@GetMapping(headers = {"action=averageOrderCostPerCustomer"})
	public ResponseEntity<ApiResponse<List<KeyValue<String, BigDecimal>>>> findAverageOrderCostPerCustomer(
			@Nullable @RequestHeader(value = "api-version", defaultValue = "1") Integer apiVersion) {
		logger.info("API Version passed is {}.", apiVersion);

		final List<KeyValue<String, BigDecimal>> results = orderReportService.findAverageOrderCostPerCustomer();
		return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, BigDecimal>>>builder().data(results).build());
	}

	@GetMapping(headers = {"action=productSaleFrequency"})
	public ResponseEntity<ApiResponse<List<KeyValue<String, Integer>>>> findProductSaleFrequency() {
		return ResponseEntity.ok(ApiResponse.<List<KeyValue<String, Integer>>>builder()
											.data(orderReportService.findProductSaleFrequency()).build());
	}
}
