package gr.codelearn.spring.cloud.showcase.core.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderItemResource extends BaseResource {
	private String serial;
	private String name;
	private String categoryDescription;
	private Integer quantity;
	private BigDecimal price;
}
