package gr.codelearn.spring.cloud.showcase.app.mapper;

import gr.codelearn.spring.cloud.showcase.app.domain.Product;
import gr.codelearn.spring.cloud.showcase.app.transfer.resource.ProductResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}, config = IgnoreUnmappedMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductResource> {
}
