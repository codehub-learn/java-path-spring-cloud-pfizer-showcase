package gr.codelearn.spring.cloud.showcase.loyalty.repository;

import gr.codelearn.spring.cloud.showcase.loyalty.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
