package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.CastsDto;
import fareye.BookMyMovie.dto.TransactionDto;
import fareye.BookMyMovie.modal.Actor;
import fareye.BookMyMovie.modal.Casts;
import fareye.BookMyMovie.modal.Movie;
import fareye.BookMyMovie.modal.Transaction;
import fareye.BookMyMovie.reposatory.ActorRepo;
import fareye.BookMyMovie.reposatory.CastRepo;
import fareye.BookMyMovie.reposatory.MovieRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CastService {
    @Autowired
    CastRepo castRepo;
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    ActorRepo actorRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> addMovieAndActors(Integer actorId, Integer movieId){
        Movie movie = movieRepo.findByMovieId(movieId);
        Actor actor = actorRepo.findByActorId(actorId);
        Casts casts = new Casts();
        casts.setMovieId(movieId);
        casts.setActorId(actorId);
        casts.setActor(actor);
        casts.setMovies(movie);
        castRepo.save(casts);
        return ResponseEntity.ok("Cast Added");
    }

    public ResponseEntity<String> addMovieAndActorsString(String actorIds, Integer movieId){
        Movie movie = movieRepo.findByMovieId(movieId);
        List<String> items = Arrays.asList(actorIds.split("\\s*,\\s*"));
        List<Integer> actors = items.stream()
                .map(Integer::valueOf).collect(Collectors.toList());
        for(int i=0;i<actors.size();i++) {
            Integer actorId = actors.get(i);
            Actor actor = actorRepo.findByActorId(actorId);
            Casts casts = new Casts();
            casts.setMovieId(movieId);
            casts.setActorId(actorId);
            casts.setActor(actor);
            casts.setMovies(movie);
            castRepo.save(casts);
        }
        return ResponseEntity.ok("Cast Added");
    }

    public ResponseEntity<List<CastsDto>> getAllCast() {
        List<Casts> casts = new ArrayList<Casts>();
        castRepo.findAll().forEach(casts::add);
        if (casts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CastsDto> castsDtos = casts.stream().map(cast -> modelMapper.map(cast,CastsDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(castsDtos, HttpStatus.OK);
    }
}
