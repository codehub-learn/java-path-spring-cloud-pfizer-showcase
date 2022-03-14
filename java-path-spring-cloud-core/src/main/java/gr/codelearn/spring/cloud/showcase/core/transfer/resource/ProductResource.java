package gr.codelearn.spring.cloud.showcase.core.transfer.resource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductResource extends BaseResource {
	private String serial;
	private String name;
	private BigDecimal price;
	private CategoryResource category;
}
