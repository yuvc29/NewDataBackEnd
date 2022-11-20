package fareye.BookMyMovie.dto;

import fareye.BookMyMovie.modal.*;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class OrdersDto {
    private Integer orderId;
    private Integer showId;
    private Integer userId;
    private Integer amount;
    private Integer couponId;
    private Integer amountAfterDiscount;
//    private List<Integer> bookedSeats;
    private Boolean status;
}
