package gr.codelearn.spring.cloud.showcase.app.mapper;

import gr.codelearn.spring.cloud.showcase.app.domain.Customer;
import gr.codelearn.spring.cloud.showcase.app.transfer.resource.CustomerResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CustomerMapper extends BaseMapper<Customer, CustomerResource> {
}
