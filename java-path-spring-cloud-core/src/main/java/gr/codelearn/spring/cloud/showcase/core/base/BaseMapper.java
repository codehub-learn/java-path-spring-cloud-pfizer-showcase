package gr.codelearn.spring.cloud.showcase.core.base;

import java.util.List;

public interface BaseMapper<E, R> {
	R toResource(E domain);

	List<R> toResources(List<E> domains);

	E toDomain(R resource);

	List<E> toDomains(List<R> resources);
}
