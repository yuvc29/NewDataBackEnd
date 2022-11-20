package fareye.BookMyMovie.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "genreLink")
public class GenreLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer genreLinkId;

    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "movie_fk"))
    private Movie movie;
    @NotNull(message = "movie Id is required and can't be null")
    private Integer movieId;

    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "genre_fk"))
    private Genre genre;
    @NotNull(message = "genre Id is required and can't be null")
    private Integer genreId;
}
