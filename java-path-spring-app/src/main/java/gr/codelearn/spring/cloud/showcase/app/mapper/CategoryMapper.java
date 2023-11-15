package gr.codelearn.spring.cloud.showcase.app.mapper;

import gr.codelearn.spring.cloud.showcase.app.domain.Category;
import gr.codelearn.spring.cloud.showcase.app.transfer.resource.CategoryResource;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = IgnoreUnmappedMapperConfig.class)
public interface CategoryMapper extends BaseMapper<Category, CategoryResource> {
}
