package gr.codelearn.spring.cloud.showcase.customer.domain;

import gr.codelearn.spring.cloud.showcase.core.domain.BaseModel;
import gr.codelearn.spring.cloud.showcase.core.domain.CustomerCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMERS", indexes = {@Index(columnList = "email")})
@SequenceGenerator(name = "idGenerator", sequenceName = "CUSTOMERS_SEQ", initialValue = 1, allocationSize = 1)
public class Customer extends BaseModel {
	@NotNull(message = "{email.null}")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{email.format}")
	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@NotNull(message = "{firstname.null}")
	@Column(length = 20, nullable = false)
	private String firstname;

	@NotNull(message = "{lastname.null}")
	@Column(length = 30, nullable = false)
	private String lastname;

	@Min(value = 12, message = "{age.min}")
	@Max(value = 120, message = "{age.max}")
	private Integer age;

	@Column(length = 50)
	@Size(max = 50, message = "{address.length}")
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	private CustomerCategory customerCategory;
}
