package gr.codelearn.spring.cloud.showcase.catalog.mapper;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Product;
import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.mapper.IgnoreUnmappedMapperConfig;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.ProductResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class}, config = IgnoreUnmappedMapperConfig.class)
public interface ProductMapper extends BaseMapper<Product, ProductResource> {
}
