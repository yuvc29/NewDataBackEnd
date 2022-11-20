package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    Orders findByOrderId(Integer orderId);

    @Query(value="SELECT table3.show_id, table3.movie_id, table3.name, table3.timing, city.name as city_name FROM city JOIN \n" +
            "(SELECT theater.name, theater.city_id, table2.movie_id, table2.timing, table2.show_id, table2.user_id FROM theater JOIN\n" +
            "(SELECT show.movie_id, show.timing, show.theater_id, table1.show_id, table1.user_id FROM show JOIN\n" +
            "(SELECT users.user_id, orders.show_id FROM orders JOIN users ON orders.user_id=users.user_id)\n" +
            "as table1 ON show.show_id=table1.show_id) \n" +
            "as table2 ON theater.theater_id=table2.theater_id) as table3 ON table3.city_id=city.city_id\n" +
            "WHERE user_id=?1", nativeQuery = true)
    List<String> findOrdersByUserId(Integer userId);
}
