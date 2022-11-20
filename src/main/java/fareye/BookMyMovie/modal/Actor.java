package fareye.BookMyMovie.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "actor",uniqueConstraints = @UniqueConstraint(columnNames = {"firstName","lastName"}))
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer actorId;
    @NotBlank(message = "actors First name can't be blank")
    private String firstName;
    @NotBlank(message = "actors Last name can't be blank")
    private String lastName;
    private String image;
    @OneToMany(mappedBy = "actor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Casts> casts;
}
