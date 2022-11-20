package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.ActorDto;
import fareye.BookMyMovie.dto.GenreDto;
import fareye.BookMyMovie.modal.Genre;
import fareye.BookMyMovie.modal.GenreLink;
import fareye.BookMyMovie.reposatory.GenreRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    GenreRepo genreRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> addGenre(GenreDto genreDto){
        Genre genre = modelMapper.map(genreDto, Genre.class);
//        genre.setGenreLinks(new ArrayList<GenreLink>());

        genreRepo.save(genre);
        return ResponseEntity.ok("Genre added");
    }

    public ResponseEntity<List<GenreDto>> getAllGenre(){
        List<Genre> genres = new ArrayList<Genre>();
        genreRepo.findAll().forEach(genres::add);
        if (genres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<GenreDto> genreDtos = genres.stream().map(genre -> modelMapper.map(genre,GenreDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(genreDtos, HttpStatus.OK);
    }

    public ResponseEntity<GenreDto> updateGenre(Integer genreId, GenreDto genreDto) {
        Genre genre = modelMapper.map(genreDto, Genre.class);
        genre.setGenreId(genreId);
        Genre updatedGenre = genreRepo.save(genre);
        GenreDto dto = modelMapper.map(updatedGenre, GenreDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteGenre(Integer genreId) {
        Genre genre = genreRepo.findByGenreId(genreId);
        genreRepo.delete(genre);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<GenreDto> getGenreById(Integer genreId) {
        GenreDto dto = modelMapper.map(genreRepo.findByGenreId(genreId), GenreDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<String>> getGenreByMovieId(Integer movieId){
        List<String> string = genreRepo.findGenreByMovieId(movieId);
        return ResponseEntity.ok(string);
    }
}
