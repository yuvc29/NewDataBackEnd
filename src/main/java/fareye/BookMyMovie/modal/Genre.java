package fareye.BookMyMovie.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor @Entity @Getter @Setter
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer genreId;
    @Column(unique = true)
    @NotBlank(message = "genre name can't be blank")
    private String name;
    @OneToMany(mappedBy = "genre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<GenreLink> genreLinks;
}
