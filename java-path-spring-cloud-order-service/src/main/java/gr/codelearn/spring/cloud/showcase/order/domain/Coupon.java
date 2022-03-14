package gr.codelearn.spring.cloud.showcase.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "COUPONS")
@SequenceGenerator(name = "idGenerator", sequenceName = "COUPONS_SEQ", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Coupon extends BaseEntity {
	@NotNull
	@Column(length = 36, nullable = false)
	private String code;

	private Float discountPercent;

	@Column(precision = 10, scale = 2)
	private BigDecimal discountAmount;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date generatedAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date expiresAt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date usedAt;
}
