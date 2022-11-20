package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.ActorDto;
import fareye.BookMyMovie.dto.MovieDto;
import fareye.BookMyMovie.modal.*;
import fareye.BookMyMovie.reposatory.CastRepo;
import fareye.BookMyMovie.reposatory.GenreLinkRepo;
import fareye.BookMyMovie.reposatory.MovieRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.Cast;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService{
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    CastRepo castRepo;
    @Autowired
    GenreLinkRepo genreLinkRepo;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity<MovieDto> addMovie(MovieDto movieDto){
        Movie movie = modelMapper.map(movieDto, Movie.class);
        Movie newMovie =  movieRepo.save(movie);
        MovieDto dto = modelMapper.map(newMovie, MovieDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<MovieDto>> getAllMovies(){
        List<Movie> movies = new ArrayList<Movie>();
        movieRepo.findAll().forEach(movies::add);
        if (movies.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<MovieDto> movieDtos = movies.stream().map(movie -> modelMapper.map(movie,MovieDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(movieDtos, HttpStatus.OK);
    }

    public ResponseEntity<MovieDto> updateMovie(Integer movieId, MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);
        movie.setMovieId(movieId);
        Movie updatedMovie = movieRepo.save(movie);
        MovieDto dto = modelMapper.map(updatedMovie, MovieDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteMovie(Integer movieId) {
        Movie movie = movieRepo.findByMovieId(movieId);
        movieRepo.delete(movie);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<MovieDto> getMovieById(Integer movieId) {
        MovieDto dto = modelMapper.map(movieRepo.findByMovieId(movieId), MovieDto.class);
        return ResponseEntity.ok(dto);
    }

//    public ResponseEntity<List<String>> getActorsByMovieId(Integer movieId) {
//        List<String> string = movieRepo.findActorsByMovieId(movieId);
//        return ResponseEntity.ok(string);
//    }
}
