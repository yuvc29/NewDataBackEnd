package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Integer> {
    Seat findBySeatId(Integer seatId);

    List<Seat> findByShowId(Integer showId);

    Seat findByShowIdAndSeatNo(Integer showId, Integer seatNo);

//    List<Integer> findSeatNoByOrderId(Integer orderId);

    List<Seat> findByOrderId(Integer orderId);
}
