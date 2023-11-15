package gr.codelearn.spring.cloud.showcase.catalog.controller;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Category;
import gr.codelearn.spring.cloud.showcase.catalog.mapper.CategoryMapper;
import gr.codelearn.spring.cloud.showcase.catalog.service.CategoryService;
import gr.codelearn.spring.cloud.showcase.core.controller.BaseController;
import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CategoryResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryResourceController extends BaseController<Category, CategoryResource> {
	private final CategoryService categoryService;
	private final CategoryMapper categoryMapper;

	@Override
	public BaseService<Category, Long> getBaseService() {
		return categoryService;
	}

	@Override
	public BaseMapper<Category, CategoryResource> getMapper() {
		return categoryMapper;
	}

	@GetMapping(params = {"description"})
	public ResponseEntity<ApiResponse<CategoryResource>> findByDescription(
			@RequestParam String description) {
		return ResponseEntity.ok(ApiResponse.<CategoryResource>builder().data(getMapper().toResource(
				categoryService.findByDescription(description))).build());
	}
}
