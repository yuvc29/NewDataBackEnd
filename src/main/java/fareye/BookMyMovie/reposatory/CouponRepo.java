package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
    Coupon findByCouponId(Integer couponId);
}
