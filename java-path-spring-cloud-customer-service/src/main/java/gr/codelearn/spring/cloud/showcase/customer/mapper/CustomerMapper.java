package gr.codelearn.spring.cloud.showcase.customer.mapper;

import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.mapper.IgnoreUnmappedMapperConfig;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CustomerResource;
import gr.codelearn.spring.cloud.showcase.customer.domain.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CustomerMapper extends BaseMapper<Customer, CustomerResource> {
}
