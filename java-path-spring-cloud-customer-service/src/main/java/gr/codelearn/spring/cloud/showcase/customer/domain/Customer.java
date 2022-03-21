package gr.codelearn.spring.cloud.showcase.customer.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.codelearn.spring.cloud.showcase.core.domain.BaseModel;
import gr.codelearn.spring.cloud.showcase.core.domain.CustomerCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "CUSTOMERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "CUSTOMERS_SEQ", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseModel {
	@NotNull
	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@NotNull
	@Column(length = 20, nullable = false)
	private String firstname;

	@NotNull
	@Column(length = 30, nullable = false)
	private String lastname;

	private Integer age;

	@Column(length = 50)
	private String address;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 10, nullable = false)
	private CustomerCategory customerCategory;
}
