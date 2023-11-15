package gr.codelearn.spring.cloud.showcase.order.mapper;

import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.mapper.IgnoreUnmappedMapperConfig;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.OrderItemResource;
import gr.codelearn.spring.cloud.showcase.order.domain.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemResource> {
}
