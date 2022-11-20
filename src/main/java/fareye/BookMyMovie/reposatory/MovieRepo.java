package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Actor;
import fareye.BookMyMovie.modal.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Integer> {
    Movie findByMovieId(Integer movieId);

//    @Query(value = "SELECT actor.actor_id, actor.first_name, actor.last_name, actor.image FROM actor JOIN casts ON casts.actor_id=actor.actor_id\n" +
//            "WHERE casts.movie_id=?1",nativeQuery = true)
//    List<String> findActorsByMovieId(Integer movieId);
}
