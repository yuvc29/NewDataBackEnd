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
@Table(name = "theater")
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer theaterId;
    @NotBlank(message = "Theater name must not be Blank")
    private String name;
    @NotBlank(message = "Theater Address must not be Blank")
    private String address;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "city_fk"))
    private City city;
    @NotNull(message = "city Id Must Not be Null")
    private Integer cityId;
    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Show> shows;
}
