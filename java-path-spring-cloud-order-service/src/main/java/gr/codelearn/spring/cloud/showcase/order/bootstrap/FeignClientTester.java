package gr.codelearn.spring.cloud.showcase.order.bootstrap;

import gr.codelearn.spring.cloud.showcase.core.base.BaseComponent;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.order.service.client.CatalogServiceClient;
import gr.codelearn.spring.cloud.showcase.order.service.client.CustomerServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Profile("feign-client-tester")
@Component
@RequiredArgsConstructor
public class FeignClientTester extends BaseComponent implements CommandLineRunner {
	private final CustomerServiceClient customerServiceClient;
	private final CatalogServiceClient catalogServiceClient;

	@Override
	public void run(String... args) {
		CustomerResource firstCustomer = Objects.requireNonNull(
				customerServiceClient.findByEmail("c.giannacoulis@codehub.gr").getBody()).getData();
		logger.info("Retrieved myself {}.", firstCustomer);

		logger.info("Retrieved the following products: {}. {}.",
					Objects.requireNonNull(catalogServiceClient.findBySerial("SN1000-0001").getBody()).getData(),
					Objects.requireNonNull(catalogServiceClient.findBySerial("SN1100-0001").getBody()).getData());

		logger.info("-----------------------------------");
		logger.info("-- Round 1 --");
		checkLoadBalancing();
		checkLoadBalancing();
		checkLoadBalancing();
		logger.info("-- Round 2 --");
		checkLoadBalancing();
		checkLoadBalancing();
		checkLoadBalancing();
		logger.info("-- Round 3 --");
		checkLoadBalancing();
		checkLoadBalancing();
		checkLoadBalancing();
		logger.info("-----------------------------------");
	}

	private void checkLoadBalancing() {
		logger.info("LB demo, {}",
					Objects.requireNonNull(customerServiceClient.checkLoadBalancing().getBody()).getData());
	}
}
