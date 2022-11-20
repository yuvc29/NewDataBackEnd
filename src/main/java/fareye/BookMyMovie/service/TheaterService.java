package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.ActorDto;
import fareye.BookMyMovie.dto.TheaterDto;
import fareye.BookMyMovie.modal.Show;
import fareye.BookMyMovie.modal.Theater;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.reposatory.CityRepo;
import fareye.BookMyMovie.reposatory.TheaterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    @Autowired
    TheaterRepo theaterRepo;
    @Autowired
    CityRepo cityRepo;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity<TheaterDto> addTheater(TheaterDto theaterDto){
        Theater theater = modelMapper.map(theaterDto, Theater.class);
        theater.setCity(cityRepo.findByCityId(theater.getCityId()));
        TheaterDto dto = modelMapper.map(theaterRepo.save(theater), TheaterDto.class);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<TheaterDto>> getAllTheater(){
        List<Theater> theaters = new ArrayList<Theater>();
        theaterRepo.findAll().forEach(theaters::add);
        if (theaters.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TheaterDto> theaterDtos = theaters.stream().map(theater -> modelMapper.map(theater,TheaterDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(theaterDtos, HttpStatus.OK);
    }

    public ResponseEntity<TheaterDto> getTheaterById(Integer theaterId) {
        TheaterDto dto = modelMapper.map(theaterRepo.findByTheaterId(theaterId), TheaterDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<TheaterDto> updateTheater(Integer theaterId, TheaterDto theaterDto) {
        Theater theater = modelMapper.map(theaterDto, Theater.class);
        theater.setTheaterId(theaterId);
        Theater updatedTheater = theaterRepo.save(theater);
        TheaterDto dto = modelMapper.map(updatedTheater, TheaterDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteTheater(Integer theaterId) {
        Theater theater = theaterRepo.findByTheaterId(theaterId);
        theaterRepo.delete(theater);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<List<TheaterDto>> getTheaterByCityId(Integer cityId) {
        List<Theater> theaters = theaterRepo.findByCityId(cityId);
        List<TheaterDto> theaterDtos = theaters.stream().map(theater -> modelMapper.map(theater,TheaterDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(theaterDtos);
    }

    public ResponseEntity<List<String>> getTheaterByCityIdMovieIdAndDate(Integer cityId, Integer movieId, String date){
        List<String> string = theaterRepo.findByCityIdMovieIdAndDate(cityId, movieId, date);
        return ResponseEntity.ok(string);
    }
}
