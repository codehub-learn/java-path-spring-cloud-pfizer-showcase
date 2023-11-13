package gr.codelearn.spring.showcase.app.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Delegate;

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
@Table(name = "ORDERS", indexes = {@Index(columnList = "customer_id")})
@SequenceGenerator(name = "idGenerator", sequenceName = "ORDERS_SEQ", initialValue = 1, allocationSize = 1)
public class Order extends BaseModel {
	private interface MinimalSet {
		boolean add(OrderItem item);

		boolean remove(OrderItem item);
	}

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Customer customer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date submitDate;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@Delegate(types = MinimalSet.class)
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	//@Fetch(FetchMode.JOIN) // Just to remember its existence
	private final Set<OrderItem> orderItems = new HashSet<>();

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	private PaymentMethod paymentMethod;

	@NotNull
	@Column(precision = 10, scale = 2, nullable = false)
	private BigDecimal cost;
}
