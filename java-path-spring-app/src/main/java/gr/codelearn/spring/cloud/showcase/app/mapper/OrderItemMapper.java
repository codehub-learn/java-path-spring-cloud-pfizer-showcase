package gr.codelearn.spring.cloud.showcase.app.mapper;

import gr.codelearn.spring.cloud.showcase.app.domain.OrderItem;
import gr.codelearn.spring.cloud.showcase.app.transfer.resource.OrderItemResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, config = IgnoreUnmappedMapperConfig.class)
public interface OrderItemMapper extends BaseMapper<OrderItem, OrderItemResource> {
}
