package fareye.BookMyMovie.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movieId;
    @NotBlank(message = "Movie Title must not be Blank")
    @Column(unique = true)
    private String title;
    @NotBlank(message = "Movie Release Date must not be Blank")
    private String releaseDate;
    @NotBlank(message = "TMovie Expiry Date must not be Blank")
    private String expiryDate;
    @NotBlank(message = "Movie Description must not be Blank")
    private String description;
    @NotBlank(message = "Movie Language must not be Blank")
    private String language;
    @NotNull(message = "Movie Length must not be null")
    private Integer length;
    @NotBlank(message = "Trailer Link must not be Blank")
    private String trailer;
    @NotBlank(message = "Poster Link must not be Blank")
    private String poster;
    private String format;
    @NotNull(message = "movie rating can't be blank")
    private Integer rating;
    @NotBlank(message = "movie age rating can't be blank")
    private String ageRating;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;
    @OneToMany(mappedBy = "movies", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Casts> casts;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GenreLink> genreLinks;
    private Integer genreLinkId;
}
