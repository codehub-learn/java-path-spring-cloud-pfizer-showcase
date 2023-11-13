package gr.codelearn.spring.showcase.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDER_ITEMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDER_ITEMS_SEQ", initialValue = 1, allocationSize = 1)
public class OrderItem extends BaseModel {
	@ToString.Exclude
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Product product;

	@ToString.Exclude
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
