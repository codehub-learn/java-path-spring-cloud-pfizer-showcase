package gr.codelearn.spring.cloud.showcase.order.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import gr.codelearn.spring.cloud.showcase.core.domain.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "ORDER_ITEMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDER_ITEMS_SEQ", initialValue = 1, allocationSize = 1)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseModel {
	@NotNull
	@Column(length = 50, nullable = false)
	private String serial;

	@NotNull
	@Column(length = 50, nullable = false)
	private String name;

	@NotNull
	@Column(length = 50, nullable = false)
	private String categoryDescription;

	@JsonBackReference("orderItems")
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Order order;

	@NotNull
	@Column(nullable = false)
	private Integer quantity;

	@NotNull
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal price;
}
