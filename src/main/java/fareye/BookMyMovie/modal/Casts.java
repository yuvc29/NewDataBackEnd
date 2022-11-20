package fareye.BookMyMovie.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "casts")
public class Casts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer castId;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "movies_fk"))
    private Movie movies;
    @NotNull(message = "movie Id can't be null")
    private Integer movieId;
    private String role;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "actor_fk"))
    private Actor actor;
    @NotNull(message = "actor Id can't be null")
    private Integer actorId;
}
