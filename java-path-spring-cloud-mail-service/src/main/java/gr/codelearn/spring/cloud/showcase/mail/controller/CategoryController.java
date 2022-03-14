package gr.codelearn.spring.cloud.showcase.mail.controller;

import gr.codelearn.spring.cloud.showcase.mail.domain.Category;
import gr.codelearn.spring.cloud.showcase.mail.service.BaseService;
import gr.codelearn.spring.cloud.showcase.mail.service.CategoryService;
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
