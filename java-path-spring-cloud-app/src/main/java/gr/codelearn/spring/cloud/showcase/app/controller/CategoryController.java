package gr.codelearn.spring.cloud.showcase.app.controller;

import gr.codelearn.spring.cloud.showcase.app.domain.Category;
import gr.codelearn.spring.cloud.showcase.app.service.BaseService;
import gr.codelearn.spring.cloud.showcase.app.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController extends AbstractController<Category> {
	private final CategoryService categoryService;

	@Override
	public BaseService<Category, Long> getBaseService() {
		return categoryService;
	}
}
