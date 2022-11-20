package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.ActorDto;
import fareye.BookMyMovie.exceptions.ThereAreNoActorsException;
import fareye.BookMyMovie.modal.Actor;
import fareye.BookMyMovie.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActorController {
    @Autowired
    ActorService actorService;

    @PostMapping("/actor")
    public ResponseEntity<String> addActor(@RequestBody ActorDto actor){
        return actorService.addActor(actor);
    }

    @GetMapping("/actor")
    public ResponseEntity<List<ActorDto>> getAllActors() throws ThereAreNoActorsException {
        return actorService.getAllActors();
    }

    @GetMapping("/actor/{id}")
    public ResponseEntity<ActorDto> getActorById(@PathVariable(value = "id") Integer actorId) {
        return actorService.getActorById(actorId);
    }

    @PutMapping("/actor/{id}")
    public ResponseEntity<ActorDto> updateActor(@PathVariable(value = "id") Integer actorId,
                                             @RequestBody ActorDto actor) {
        return actorService.updateActor(actorId, actor);
    }

    @DeleteMapping("/actor/{id}")
    public ResponseEntity<String> deleteActor(@PathVariable(value = "id") Integer actorId) {
        return actorService.deleteActor(actorId);
    }
    @GetMapping("/actors/{movie_id}")
    public ResponseEntity<List<String>> getAllActorsByMovieId(@PathVariable(value = "movie_id") Integer movieId){
        return actorService.getActorsByMovieId(movieId);
    }
}
