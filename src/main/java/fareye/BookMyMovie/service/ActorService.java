package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.ActorDto;
import fareye.BookMyMovie.dto.CardDto;
import fareye.BookMyMovie.exceptions.ThereAreNoActorsException;
import fareye.BookMyMovie.modal.Actor;
import fareye.BookMyMovie.modal.Card;
import fareye.BookMyMovie.modal.Casts;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.reposatory.ActorRepo;
import fareye.BookMyMovie.reposatory.MovieRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorService {
    @Autowired
    ActorRepo actorRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> addActor(ActorDto actorDto){
        Actor actor = modelMapper.map(actorDto, Actor.class);

//        actor.setCasts(new ArrayList<Casts>());
        actorRepo.save(actor);
        return ResponseEntity.ok("Actor Added");
    }

    public ResponseEntity<List<ActorDto>> getAllActors() throws ThereAreNoActorsException {
        List<Actor> actors = new ArrayList<Actor>();
        actorRepo.findAll().forEach(actors::add);
        if (actors.isEmpty()) {
            throw new ThereAreNoActorsException("there are no actors in list");
        }
        List<ActorDto> actorDtos = actors.stream().map(actor -> modelMapper.map(actor,ActorDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(actorDtos, HttpStatus.OK);
    }

    public ResponseEntity<ActorDto> updateActor(Integer actorId, ActorDto actorDto) {
        Actor actor = actorRepo.findByActorId(actorId);
        actor.setActorId(actorId);
        Actor updatedActor = actorRepo.save(actor);
        ActorDto dto = modelMapper.map(updatedActor, ActorDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteActor(Integer actorId) {
        Actor actor = actorRepo.findByActorId(actorId);
        actorRepo.delete(actor);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<ActorDto> getActorById(Integer actorId) {
        ActorDto dto = modelMapper.map(actorRepo.findByActorId(actorId), ActorDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<List<String>> getActorsByMovieId(Integer movieId) {
        List<String> string = actorRepo.findActorsByMovieId(movieId);
        return ResponseEntity.ok(string);
    }

//    public ResponseEntity<List<ActorDto>> getActorsByMovieId(Integer movieId){
//        List<Actor> actors = actorRepo.findAllActorsByMovieId(movieId);
//        List<ActorDto> actorDtos = actors.stream().map(actor -> modelMapper.map(actor,ActorDto.class)).collect(Collectors.toList());
//        return ResponseEntity.ok(actorDtos);
//    }

}
