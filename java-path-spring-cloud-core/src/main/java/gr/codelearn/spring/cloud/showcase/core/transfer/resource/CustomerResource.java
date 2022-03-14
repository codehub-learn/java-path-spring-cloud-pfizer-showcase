package gr.codelearn.spring.cloud.showcase.core.transfer.resource;

import gr.codelearn.spring.cloud.showcase.core.domain.CustomerCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CustomerResource extends BaseResource {
	private String email;
	private String firstname;
	private String lastname;
	private Integer age;
	private String address;
	private CustomerCategory customerCategory;
}
