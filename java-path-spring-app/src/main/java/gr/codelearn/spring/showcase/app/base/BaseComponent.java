package gr.codelearn.spring.showcase.app.base;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseComponent {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		logger.trace("Loaded {}.", getClass());
	}

	@PreDestroy
	public void destroy() {
		logger.trace("{} is about to be destroyed.", getClass().getName());
	}
}
