package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.MovieDto;
import fareye.BookMyMovie.modal.Actor;
import fareye.BookMyMovie.modal.Movie;
import fareye.BookMyMovie.modal.Theater;
import fareye.BookMyMovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/movie")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie){
        return movieService.addMovie(movie);
    }

    @GetMapping("/movie")
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(value = "id") Integer movieId) {
        return movieService.getMovieById(movieId);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable(value = "id") Integer movieId,
                                                 @RequestBody MovieDto movie) {
        return movieService.updateMovie(movieId, movie);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(value = "id") Integer movieId) {
        return movieService.deleteMovie(movieId);
    }

//    @GetMapping("/actors/{movie_id}")
//    public ResponseEntity<List<String>> getAllActorsByMovieId(@PathVariable(value = "movie_id") Integer movieId){
//        return movieService.getActorsByMovieId(movieId);
//    }
}
