package gr.codelearn.spring.cloud.showcase.core.transfer.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class CategoryResource extends BaseResource {
	private String description;
}
