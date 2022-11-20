package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.TheaterDto;
import fareye.BookMyMovie.modal.Theater;
import fareye.BookMyMovie.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TheaterController {
    @Autowired
    TheaterService theaterService;

    @PostMapping("/theater")
    public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto theater){
        return theaterService.addTheater(theater);
    }

    @GetMapping("/theater")
    public ResponseEntity<List<TheaterDto>> getAllTheater(){
        return theaterService.getAllTheater();
    }

    @GetMapping("/theater/{id}")
    public ResponseEntity<TheaterDto> getTheaterById(@PathVariable(value = "id") Integer theaterId) {
        return theaterService.getTheaterById(theaterId);
    }

    @PutMapping("/theater/{id}")
    public ResponseEntity<TheaterDto> updateTheater(@PathVariable(value = "id") Integer theaterId,
                                           @RequestBody TheaterDto theater) {
        return theaterService.updateTheater(theaterId, theater);
    }

    @DeleteMapping("/theater/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable(value = "id") Integer theaterId) {
        return theaterService.deleteTheater(theaterId);
    }

    @GetMapping("/theater/city/{id}")
    public ResponseEntity<List<TheaterDto>> getTheaterByCityId(@PathVariable(value = "id") Integer cityId) {
        return theaterService.getTheaterByCityId(cityId);
    }
    @GetMapping("/theater/cityMovieDate")
    public ResponseEntity<List<String>> getTheaterByCityIdMovieIdAndDate(@RequestParam Integer cityId, @RequestParam Integer movieId, @RequestParam String date){
        return theaterService.getTheaterByCityIdMovieIdAndDate(cityId, movieId, date);
    }
}
