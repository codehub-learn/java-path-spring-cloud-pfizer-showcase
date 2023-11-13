package gr.codelearn.spring.showcase.app.service;

import java.util.List;

public interface BaseService<T, K> {
	T create(final T item);

	List<T> createAll(final T... items);

	List<T> createAll(final List<T> items);

	void update(T item);

	void delete(T item);

	void deleteById(K id);

	boolean exists(T item);

	T get(K id);

	List<T> findAll();

	Long count();
}
