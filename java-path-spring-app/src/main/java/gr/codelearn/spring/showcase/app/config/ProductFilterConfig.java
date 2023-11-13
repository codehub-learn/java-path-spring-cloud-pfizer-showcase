package gr.codelearn.spring.showcase.app.config;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ProductFilterConfig {
	private static final Set<String> ignorableFieldNames = new HashSet<>();

	static {
		/*
		Setting the fields that will be ignored when products are returned in a response.
		If we want the full product to be returned in a response, then we should not add any fields.
		If we want to choose between showing the full product or a filtered product, we can set
		the default mechanism (described here) to include no filtered fields, and then use a custom object mapper as
		already described in the javadoc of this class.
		 */
		ignorableFieldNames.add("name");
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonFilterCustomizer() {
		SimpleFilterProvider simpleFilterProvider = new SimpleFilterProvider().setFailOnUnknownId(false);
		FilterProvider filters = simpleFilterProvider.addFilter("product_filter",
																SimpleBeanPropertyFilter.serializeAllExcept(
																		ignorableFieldNames));
		// We can also add automatic filters such as not showing nulls etc.
		//builder.serializationInclusion(JsonInclude.Include.NON_NULL)
		return builder -> builder.filters(filters);
	}
}
