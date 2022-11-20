package fareye.BookMyMovie.dto;

import lombok.Data;

@Data
public class CardDto {
    private Integer cardId;
    private Long cardNo;
    private String name;
    private Integer cvv;
    private String expDate;
    private Integer userId;
}
