package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.GenreDto;
import fareye.BookMyMovie.modal.Genre;
import fareye.BookMyMovie.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {
    @Autowired
    GenreService genreService;

    @PostMapping("/genre")
    public ResponseEntity<String> addGenre(@RequestBody GenreDto genre){
        return genreService.addGenre(genre);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<GenreDto>> getAllGenre(){
        return genreService.getAllGenre();
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<GenreDto> getGenreById(@PathVariable(value = "id") Integer genreId) {
        return genreService.getGenreById(genreId);
    }

    @PutMapping("/genre/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable(value = "id") Integer genreId,
                                                 @RequestBody GenreDto genre) {
        return genreService.updateGenre(genreId, genre);
    }

    @DeleteMapping("/genre/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable(value = "id") Integer genreId) {
        return genreService.deleteGenre(genreId);
    }

    @GetMapping("/genre/movie")
    public ResponseEntity<List<String>> getGenreByMovieId(@RequestParam Integer movieId){
        return genreService.getGenreByMovieId(movieId);
    }
}
