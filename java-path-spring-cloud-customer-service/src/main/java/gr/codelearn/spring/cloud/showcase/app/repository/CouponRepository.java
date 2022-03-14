package gr.codelearn.spring.cloud.showcase.app.repository;

import gr.codelearn.spring.cloud.showcase.app.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
