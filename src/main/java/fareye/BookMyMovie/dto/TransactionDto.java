package fareye.BookMyMovie.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Integer transactionId;
    private Integer orderId;
    private Boolean status;
}
