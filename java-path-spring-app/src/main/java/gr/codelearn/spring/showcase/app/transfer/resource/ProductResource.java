package gr.codelearn.spring.showcase.app.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class ProductResource extends BaseResource {
	private String serial;
	private String name;
	private BigDecimal price;
	private CategoryResource category;
}
