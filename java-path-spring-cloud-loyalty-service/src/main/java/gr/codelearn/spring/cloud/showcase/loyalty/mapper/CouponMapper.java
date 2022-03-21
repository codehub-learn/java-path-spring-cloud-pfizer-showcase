package gr.codelearn.spring.cloud.showcase.loyalty.mapper;

import gr.codelearn.spring.cloud.showcase.core.base.BaseMapper;
import gr.codelearn.spring.cloud.showcase.core.transfer.resource.CouponResource;
import gr.codelearn.spring.cloud.showcase.loyalty.domain.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper extends BaseMapper<Coupon, CouponResource> {
}
