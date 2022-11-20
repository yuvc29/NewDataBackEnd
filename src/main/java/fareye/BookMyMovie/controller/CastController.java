package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.CastsDto;
import fareye.BookMyMovie.dto.SeatDto;
import fareye.BookMyMovie.reposatory.CastRepo;
import fareye.BookMyMovie.service.CastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CastController {
    @Autowired
    CastService castService;
    @PostMapping("/cast")
    public ResponseEntity<String> addMovieAndActors(@RequestParam Integer movieId, @RequestParam Integer actorId){
        return castService.addMovieAndActors(actorId, movieId);
    }

    @PostMapping("/cast/movie")
    public ResponseEntity<String> addMovieAndActorsString(@RequestParam Integer movieId, @RequestParam String actorIds){
        return castService.addMovieAndActorsString(actorIds, movieId);
    }
    @GetMapping("/cast")
    public ResponseEntity<List<CastsDto>> getAllCast(){
        return castService.getAllCast();
    }
}
