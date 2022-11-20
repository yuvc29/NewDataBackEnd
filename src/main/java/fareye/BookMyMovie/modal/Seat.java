package fareye.BookMyMovie.modal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer seatId;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "show_fk"))
    private Show show;
    private Integer showId;
    private Integer seatNo;
    private Integer price;
    @ManyToOne( cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "orders_fk"))
    private Orders orders;
    private Integer orderId;
}
