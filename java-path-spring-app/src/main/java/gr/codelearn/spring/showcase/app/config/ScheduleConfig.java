package gr.codelearn.spring.showcase.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduleConfig {
	/* In case we need to define task scheduler programmatically
	@Bean
	public TaskScheduler getTaskScheduler() {
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(3);
		taskScheduler.setThreadNamePrefix("schedulerBean-");
		return taskScheduler;
	}
	*/
}
