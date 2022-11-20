package fareye.BookMyMovie.dto;

import lombok.Data;

@Data
public class SeatDto {
    private Integer seatId;
    private Integer showId;
    private Integer seatNo;
    private Integer price;
    private Integer orderId;
}
