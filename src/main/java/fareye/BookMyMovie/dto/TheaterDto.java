package fareye.BookMyMovie.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TheaterDto {
    private Integer theaterId;
    @NotBlank(message = "Theater name must not be Blank")
    private String name;
    @NotBlank(message = "Theater Address must not be Blank")
    private String address;
    @NotNull(message = "city Id Must Not be Null")
    private Integer cityId;
}
