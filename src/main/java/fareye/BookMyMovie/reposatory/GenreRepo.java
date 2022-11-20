package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre, Integer> {
    Genre findByGenreId(Integer genreId);

    @Query(value = "SELECT genre.name FROM genre JOIN genre_link ON genre.genre_id=genre_link.genre_id\n" +
            "WHERE movie_id=?1", nativeQuery = true)
    List<String> findGenreByMovieId(Integer movieId);
}
