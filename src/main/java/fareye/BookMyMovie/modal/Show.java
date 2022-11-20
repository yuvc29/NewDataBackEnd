package fareye.BookMyMovie.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "show",uniqueConstraints = @UniqueConstraint(columnNames = {"movieId","theaterId","timing"}))
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer showId;
    @NotBlank(message = "you Must Specify timing of the show")
    private String timing;
    @NotBlank(message = "you Must Specify date of the show")
    private String date;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "theater_fk"))
    private Theater theater;
    @NotNull(message = "you Must Specify Theater Id of the show")
    private Integer theaterId;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "movie_fk"))
    private Movie movie;
    @NotNull(message = "you Must Specify Movie Id of the show")
    private Integer movieId;
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Orders> orders;
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;
}
