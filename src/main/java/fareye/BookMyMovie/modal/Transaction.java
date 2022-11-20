package fareye.BookMyMovie.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer transactionId;
    @NotNull(message = "order Id must not be null")
    private Integer orderId;
    @NotNull(message = "kindly provide the status of transaction")
    private Boolean status;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "order_fk"))
    private Orders order;
}
