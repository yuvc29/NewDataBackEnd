package fareye.BookMyMovie.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MovieDto {

    private Integer movieId;
    @NotBlank(message = "Movie Title must not be Blank")
    private String title;
    @NotBlank(message = "Movie Release Date must not be Blank")
    private String releaseDate;
    @NotBlank(message = "Movie Expiry Date must not be Blank")
    private String expiryDate;
    @NotBlank(message = "Movie Description must not be Blank")
    private String description;
    @NotBlank(message = "Movie Language must not be Blank")
    private String language;
    @NotNull(message = "Movie Length must not be Blank")
    private Integer length;
    @NotBlank(message = "Trailer Link must not be Blank")
    private String trailer;
    @NotBlank(message = "Poster Link must not be Blank")
    private String poster;
    private String format;
    private Integer rating;
    private String ageRating;


}
