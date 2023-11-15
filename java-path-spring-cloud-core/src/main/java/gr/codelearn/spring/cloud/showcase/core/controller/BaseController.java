package gr.codelearn.spring.cloud.showcase.core.controller;

import gr.codelearn.spring.cloud.showcase.core.base.BaseComponent;
import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.service.BaseService;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.util.WebUtils;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public abstract class BaseController<E, R> extends BaseComponent {
	protected abstract BaseService<E, Long> getBaseService();

	protected abstract BaseMapper<E, R> getMapper();

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<R>> findById(@PathVariable("id") final Long id) {
		return ResponseEntity.ok(
				ApiResponse.<R>builder().data(getMapper().toResource(getBaseService().get(id))).build());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<R>>> findAll() {
		return ResponseEntity.ok(
				ApiResponse.<List<R>>builder().data(getMapper().toResources(getBaseService().findAll())).build());
	}

	@PostMapping
	public ResponseEntity<ApiResponse<R>> create(@Valid @RequestBody final R resource) {
		return new ResponseEntity<>(ApiResponse.<R>builder().data(getMapper().toResource(
				getBaseService().create(getMapper().toDomain(resource)))).build(), WebUtils.getNoCacheHeaders(),
									HttpStatus.CREATED);
	}

	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@Valid @RequestBody final R resource) {
		getBaseService().update(getMapper().toDomain(resource));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") final Long id) {
		getBaseService().deleteById(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@Valid @RequestBody final R resource) {
		if (getBaseService().exists(getMapper().toDomain(resource))) {
			getBaseService().delete(getMapper().toDomain(resource));
		}
	}
}
