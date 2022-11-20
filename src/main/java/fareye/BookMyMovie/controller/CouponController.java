package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.CouponDto;
import fareye.BookMyMovie.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CouponController {
    @Autowired
    CouponService couponService;

    @PostMapping("/coupon")
    public ResponseEntity<String> addCoupon(@RequestBody CouponDto coupon){
        return couponService.addCoupon(coupon);
    }

    @GetMapping("/coupon")
    public ResponseEntity<List<CouponDto>> getAllCoupon(){
        return couponService.getAllCoupon();
    }

    @GetMapping("/coupon/{id}")
    public ResponseEntity<CouponDto> getCouponById(@PathVariable(value = "id") Integer couponId) {
        return couponService.getCouponById(couponId);
    }

    @PutMapping("/coupon/{id}")
    public ResponseEntity<CouponDto> updateCoupon(@PathVariable(value = "id") Integer couponId,
                                                 @RequestBody CouponDto coupon) {
        return couponService.updateCoupon(couponId, coupon);
    }

    @DeleteMapping("/coupon/{id}")
    public ResponseEntity<String> deleteCoupon(@PathVariable(value = "id") Integer couponId) {
        return couponService.deleteCoupon(couponId);
    }
}
