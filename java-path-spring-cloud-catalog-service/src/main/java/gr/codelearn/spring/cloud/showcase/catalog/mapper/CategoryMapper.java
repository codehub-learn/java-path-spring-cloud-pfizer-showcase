package gr.codelearn.spring.cloud.showcase.catalog.mapper;

import gr.codelearn.spring.cloud.showcase.catalog.domain.Category;
import gr.codelearn.spring.cloud.showcase.core.mapper.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.mapper.IgnoreUnmappedMapperConfig;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CategoryMapper extends BaseMapper<Category, CategoryResource> {
}
