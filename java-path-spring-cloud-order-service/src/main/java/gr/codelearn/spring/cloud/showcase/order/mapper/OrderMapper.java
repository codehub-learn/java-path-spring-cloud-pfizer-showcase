package gr.codelearn.spring.cloud.showcase.order.mapper;

import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.mapper.IgnoreUnmappedMapperConfig;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderResource;
import gr.codelearn.spring.cloud.showcase.order.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class}, config = IgnoreUnmappedMapperConfig.class)
public interface OrderMapper extends BaseMapper<Order, OrderResource> {
}
