package gr.codelearn.spring.cloud.showcase.catalog.component;

import gr.codelearn.spring.cloud.showcase.core.base.AbstractLogComponent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictor extends AbstractLogComponent {
	@CacheEvict(value = "catalog", allEntries = true)
	@Scheduled(cron = "0 0/1 * * * ?")
	public void clearAllCaches() {
		logger.info("Clearing catalog cache.");
	}
}
