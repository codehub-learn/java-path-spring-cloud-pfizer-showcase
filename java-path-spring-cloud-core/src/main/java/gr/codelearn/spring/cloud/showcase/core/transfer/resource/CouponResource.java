package gr.codelearn.spring.cloud.showcase.core.transfer.resource;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class CouponResource extends BaseResource {
	private String code;
	private Float discountPercent;
	private BigDecimal discountAmount;
	private Date generatedAt;
	private Date expiresAt;
	private Date usedAt;
}
