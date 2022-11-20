package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Theater;
import fareye.BookMyMovie.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TheaterRepo extends JpaRepository<Theater, Integer> {

    Theater findByTheaterId(Integer theaterId);

    List<Theater> findByCityId(Integer cityId);

    @Query(value="SELECT theater.theater_id, theater.name, theater.address FROM theater JOIN show ON theater.theater_id=show.theater_id \n" +
            "WHERE theater.city_id=?1 AND show.movie_id=?2 AND show.date=?3",nativeQuery = true)
    List<String> findByCityIdMovieIdAndDate(Integer cityId, Integer movieId, String date);
}
