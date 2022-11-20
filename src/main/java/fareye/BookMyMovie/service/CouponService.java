package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.CouponDto;
import fareye.BookMyMovie.modal.Orders;
import fareye.BookMyMovie.modal.Show;
import fareye.BookMyMovie.modal.Coupon;
import fareye.BookMyMovie.reposatory.CouponRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponService {
    @Autowired
    CouponRepo couponRepo;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity<String> addCoupon(CouponDto couponDto){
        Coupon coupon = modelMapper.map(couponDto, Coupon.class);
//        coupon.setOrders(new ArrayList<Orders>());
        couponRepo.save(coupon);
        return ResponseEntity.ok("Coupon added");
    }

    public ResponseEntity<List<CouponDto>> getAllCoupon(){
        List<Coupon> coupons = new ArrayList<Coupon>();
        couponRepo.findAll().forEach(coupons::add);
        if (coupons.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CouponDto> couponDtos = coupons.stream().map(coupon -> modelMapper.map(coupon,CouponDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(couponDtos, HttpStatus.OK);
    }

    public ResponseEntity<CouponDto> getCouponById(Integer couponId) {
        CouponDto dto = modelMapper.map(couponRepo.findByCouponId(couponId), CouponDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<CouponDto> updateCoupon(Integer couponId, CouponDto couponDto) {
        Coupon coupon = modelMapper.map(couponDto, Coupon.class);
        coupon.setCouponId(couponId);
        Coupon updatedCoupon = couponRepo.save(coupon);
        CouponDto dto = modelMapper.map(updatedCoupon, CouponDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteCoupon(Integer couponId) {
        Coupon coupon = couponRepo.findByCouponId(couponId);
        couponRepo.delete(coupon);
        return ResponseEntity.ok("Deleted Successfully");
    }
}
