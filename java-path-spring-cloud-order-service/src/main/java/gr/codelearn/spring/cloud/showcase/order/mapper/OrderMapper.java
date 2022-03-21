package gr.codelearn.spring.cloud.showcase.order.mapper;

import gr.codelearn.spring.cloud.showcase.core.base.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper extends BaseMapper<Order, OrderResource> {
	@Mapping(target = "paymentMethod", ignore = true)
	@Mapping(target = "customer.address", ignore = true)
	@Mapping(target = "customer.age", ignore = true)
	OrderResource toResource(Order order);
}
