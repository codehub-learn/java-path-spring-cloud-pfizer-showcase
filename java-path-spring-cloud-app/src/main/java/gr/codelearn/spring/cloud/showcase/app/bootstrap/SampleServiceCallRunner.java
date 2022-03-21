package gr.codelearn.spring.cloud.showcase.app.bootstrap;

import gr.codelearn.spring.cloud.showcase.core.base.AbstractLogComponent;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiError;
import gr.codelearn.spring.cloud.showcase.core.transfer.ApiResponse;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CategoryResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.ProductResource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SampleServiceCallRunner extends AbstractLogComponent implements CommandLineRunner {
	public static final String ORDER_SERVICE = "http://localhost:10400/";
	public static final String CUSTOMER_SERVICE = "http://localhost:10100/";
	public static final String CATALOG_SERVICE = "http://localhost:10000/";
	private final RestTemplate restTemplate;

	@Override
	public void run(final String... args) {
		retrieveOrder();
		retrieveCustomer();
		retrieveCustomers();
		retrieveCategories();
		retrieveProducts();
	}

	public void retrieveOrder() {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<OrderResource>> responseEntity = restTemplate.exchange(
					ORDER_SERVICE + "orders/1",
					HttpMethod.GET,
					new HttpEntity<>(getHttpHeaders()),
					new ParameterizedTypeReference<>() {
					});
			//@formatter:on

			checkForErrors(responseEntity);
			OrderResource orderResource = Objects.requireNonNull(responseEntity.getBody()).getData();

			logger.debug("Received order submitted by {} costing {}.", orderResource.getEmail(),
						 orderResource.getCost());
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call.", rce);
		}
	}

	private void retrieveCustomer() {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<CustomerResource>> responseEntity = restTemplate.exchange(
					CUSTOMER_SERVICE + "customers/3",
					HttpMethod.GET,
					new HttpEntity<>(getHttpHeaders()),
					new ParameterizedTypeReference<>() {
					});
			//@formatter:on

			checkForErrors(responseEntity);
			CustomerResource customerResource = Objects.requireNonNull(responseEntity.getBody()).getData();

			logger.debug("Received customer {} {} with id {}.", customerResource.getFirstname(),
						 customerResource.getLastname(), customerResource.getId());
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call.", rce);
		}
	}

	private void retrieveCustomers() {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<List<CustomerResource>>> responseEntity = restTemplate.exchange(
					CUSTOMER_SERVICE + "customers",
					HttpMethod.GET,
					new HttpEntity<>(getHttpHeaders()),
					new ParameterizedTypeReference<>() {
					});
			//@formatter:on

			checkForErrors(responseEntity);
			List<CustomerResource> customerResources = Objects.requireNonNull(responseEntity.getBody()).getData();

			logger.debug("Received a list of {} customers.", customerResources.size());
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call.", rce);
		}
	}

	private void retrieveCategories() {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<List<CategoryResource>>> responseEntity = restTemplate.exchange(
					CATALOG_SERVICE + "categories",
					HttpMethod.GET,
					new HttpEntity<>(getHttpHeaders()),
					new ParameterizedTypeReference<>() {
					});
			//@formatter:on

			checkForErrors(responseEntity);
			List<CategoryResource> categoryResources = Objects.requireNonNull(responseEntity.getBody()).getData();

			logger.debug("Received a list of {} categories.", categoryResources.size());
			categoryResources.forEach(c -> logger.debug("{}", c));
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call.", rce);
		}
	}

	private void retrieveProducts() {
		try {
			//@formatter:off
			ResponseEntity<ApiResponse<List<ProductResource>>> responseEntity = restTemplate.exchange(
					CATALOG_SERVICE + "products",
					HttpMethod.GET,
					new HttpEntity<>(getHttpHeaders()),
					new ParameterizedTypeReference<>() {
					});
			//@formatter:on

			checkForErrors(responseEntity);
			List<ProductResource> productResources = Objects.requireNonNull(responseEntity.getBody()).getData();

			logger.debug("Received a list of {} products.", productResources.size());
			productResources.forEach(p -> logger.debug("{}", p));
		} catch (RestClientException rce) {
			logger.error("Unable to complete the service call.", rce);
		}
	}

	private <T> void checkForErrors(final ResponseEntity<ApiResponse<T>> response) throws RestClientException {
		if (response.getStatusCodeValue() >= 300 || Objects.requireNonNull(response.getBody()).getApiError() != null) {
			ApiError apiError = response.getBody().getApiError();
			throw new RestClientException(
					String.format("Service return an error with code %d. Detailed message: %s.", apiError.getStatus(),
								  apiError.getMessage()));
		}

		if (response.getBody().getData() == null) {
			throw new RestClientException(
					String.format("Although call was completed with status %d, there was no data returned.",
								  response.getStatusCode().value()));
		}
	}

	private static HttpHeaders getHttpHeaders() {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return headers;
	}
}
