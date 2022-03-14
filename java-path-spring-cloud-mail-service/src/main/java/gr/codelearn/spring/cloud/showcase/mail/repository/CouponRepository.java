package gr.codelearn.spring.cloud.showcase.mail.repository;

import gr.codelearn.spring.cloud.showcase.mail.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
