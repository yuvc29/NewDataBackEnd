package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowRepo extends JpaRepository<Show, Integer> {

    Show findByShowId(Integer showId);

    @Query(value = "SELECT show_id, timing FROM show \n" +
            "WHERE show.theater_id=?1 AND show.movie_id=?2 AND show.date=?3", nativeQuery = true)
    List<String> getTimingByTheaterIdMovieIdAndDate(Integer theaterId, Integer movieId, String date);

//    List<Show> getAllByTheaterIdAndMovieIdAndDate(Integer theaterId, Integer movieId, String date);
}
