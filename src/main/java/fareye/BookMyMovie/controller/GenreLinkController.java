package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.CastsDto;
import fareye.BookMyMovie.service.CastService;
import fareye.BookMyMovie.service.GenreLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GenreLinkController {
    @Autowired
    GenreLinkService genreLinkService;
    @PostMapping("/genreLink")
    public ResponseEntity<String> addMovieAndGenre(@RequestParam Integer movieId, @RequestParam Integer genreId){
        return genreLinkService.addMovieAndGenre(movieId, genreId);
    }

    @PostMapping("/postGenre")
    public ResponseEntity<String> addGenreToMovie(@RequestBody Map<String,String> ids){
        Integer x = Integer.parseInt(ids.get("movieId"));
        String y =    ids.get("genreIds");
        return genreLinkService.addGenreToMovie(x,y);
    }
}