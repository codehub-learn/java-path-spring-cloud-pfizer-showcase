package gr.codelearn.spring.cloud.showcase.app.transfer.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import gr.codelearn.spring.cloud.showcase.app.domain.PaymentMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
public class OrderResource extends BaseResource {
	private CustomerResource customer;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private Date submitDate;
	private Set<OrderItemResource> orderItems = new HashSet<>();
	private PaymentMethod paymentMethod;
	private BigDecimal cost;
}
