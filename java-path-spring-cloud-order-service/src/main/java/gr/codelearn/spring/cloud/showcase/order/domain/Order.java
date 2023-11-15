package gr.codelearn.spring.cloud.showcase.order.domain;

import gr.codelearn.spring.cloud.showcase.core.domain.BaseModel;
import gr.codelearn.spring.cloud.showcase.core.domain.CustomerCategory;
import gr.codelearn.spring.cloud.showcase.core.domain.PaymentMethod;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel {
	@NotNull
	@Column(length = 50, nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column(length = 10, nullable = false)
	private CustomerCategory customerCategory;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private final Set<OrderItem> orderItems = new HashSet<>();

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private PaymentMethod paymentMethod;

	@NotNull
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal cost;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date submitDate;
}
