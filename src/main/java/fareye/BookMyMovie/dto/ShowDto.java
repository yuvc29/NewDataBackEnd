package fareye.BookMyMovie.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ShowDto {

    private Integer showId;
    @NotBlank(message = "you Must Specify timing of the show")
    private String timing;
    @NotBlank(message = "you Must Specify date of the show")
    private String date;
    @NotNull(message = "you Must Specify Theater Id of the show")
    private Integer theaterId;
    @NotNull(message = "you Must Specify Movie Id of the show")
    private Integer movieId;
}
