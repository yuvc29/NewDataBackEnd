package fareye.BookMyMovie.dto;

import lombok.Data;

@Data
public class CastsDto {
    private Integer castId;
    private Integer  movieId;
    private String role;
    private Integer actorId;
}
