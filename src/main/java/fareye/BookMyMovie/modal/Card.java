package fareye.BookMyMovie.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
//@
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cardId;
//    @NotNull(message = "card number can't be null")
    private Long cardNo;
//    @NotBlank(message = "card holders name can't be blank")
    private String name;
//    @NotNull(message = "cvv can't be null")
    private Integer cvv;
//    @NotBlank(message = "card expiry date can't be blank")
    private String expDate;
    private Integer userId;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "user_fk"))
    private Users user;
}
