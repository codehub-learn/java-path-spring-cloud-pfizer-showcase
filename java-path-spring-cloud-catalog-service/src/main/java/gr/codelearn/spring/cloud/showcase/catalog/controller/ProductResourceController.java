package gr.codelearn.spring.cloud.showcase.catalog.controller;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Product;
import gr.codelearn.spring.cloud.showcase.catalog.mapper.ProductMapper;
import gr.codelearn.spring.cloud.showcase.catalog.service.ProductService;
import gr.codelearn.spring.cloud.showcase.core.controller.BaseController;
import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.ProductResource;
import gr.codelearn.spring.cloud.showcase.core.util.WebUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductResourceController extends BaseController<Product, ProductResource> {
	private final ProductService productService;
	private final ProductMapper productMapper;

	@Override
	public BaseService<Product, Long> getBaseService() {
		return productService;
	}

	@Override
	public BaseMapper<Product, ProductResource> getMapper() {
		return productMapper;
	}

	@PostMapping(params = "categoryId")
	public ResponseEntity<ApiResponse<ProductResource>> create(@Valid @RequestBody final ProductResource resource,
															   @RequestParam Long categoryId) {
		return new ResponseEntity<>(ApiResponse.<ProductResource>builder()
											   .data(getMapper().toResource(
													   productService.create(getMapper().toDomain(resource),
																			 categoryId)))
											   .build(), WebUtils.getNoCacheHeaders(), HttpStatus.CREATED);
	}

	@GetMapping(params = "serial")
	public ResponseEntity<ApiResponse<ProductResource>> findBySerial(@RequestParam String serial) {
		final ProductResource productResource = getMapper().toResource(productService.findBySerial(serial));
		return ResponseEntity.ok(ApiResponse.<ProductResource>builder().data(productResource).build());
	}
}
