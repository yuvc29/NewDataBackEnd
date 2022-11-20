package fareye.BookMyMovie.service;

import fareye.BookMyMovie.modal.Genre;
import fareye.BookMyMovie.modal.GenreLink;
import fareye.BookMyMovie.modal.Movie;
import fareye.BookMyMovie.reposatory.GenreLinkRepo;
import fareye.BookMyMovie.reposatory.GenreRepo;
import fareye.BookMyMovie.reposatory.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreLinkService {
    @Autowired
    GenreLinkRepo genreLinkRepo;
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    GenreRepo genreRepo;
    public ResponseEntity<String> addMovieAndGenre(Integer movieId, Integer genreId) {
        Movie movie = movieRepo.findByMovieId(movieId);
        Genre genre = genreRepo.findByGenreId(genreId);
        GenreLink genreLink = new GenreLink();
        genreLink.setMovieId(movieId);
        genreLink.setGenreId(genreId);
        genreLink.setGenre(genre);
        genreLink.setMovie(movie);
        genreLinkRepo.save(genreLink);
        return ResponseEntity.ok("Genre Linked");
    }

    public ResponseEntity<String> addGenreToMovie(Integer x, String y) {
        Integer movieId = x;
        List<String> items = Arrays.asList(y.split("\\s*,\\s*"));
        List<Integer> integerList = items.stream()
                .map(Integer::valueOf).collect(Collectors.toList());

        for(Integer i=0;i<integerList.size();i++){
            Integer genreId = integerList.get(i);
            addMovieAndGenre(movieId,genreId);
        }
        return ResponseEntity.ok("Genre's added");
    }
}
