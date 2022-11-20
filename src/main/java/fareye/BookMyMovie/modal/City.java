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
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cityId;
    @NotBlank(message = "city name can't be blank")
    private String name;
    @Column(unique = true)
    @NotNull(message = "city pincode can't be blank")
    private Integer pinCode;
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Theater> theaters;
}

