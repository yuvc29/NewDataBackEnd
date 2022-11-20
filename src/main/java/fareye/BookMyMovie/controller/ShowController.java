package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.ShowDto;
import fareye.BookMyMovie.modal.Movie;
import fareye.BookMyMovie.modal.Show;
import fareye.BookMyMovie.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/show")
    public ResponseEntity<ShowDto> addTheater(@RequestBody ShowDto show){
        return showService.addShow(show);
    }

    @GetMapping("/show")
    public ResponseEntity<List<ShowDto>> getAllShow(){
        return showService.getAllShow();
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable(value = "id") Integer showId) {
        return showService.getShowById(showId);
    }

    @PutMapping("/show/{id}")
    public ResponseEntity<ShowDto> updateShow(@PathVariable(value = "id") Integer showId,
                                                 @RequestBody ShowDto show) {
        return showService.updateShow(showId, show);
    }

    @DeleteMapping("/show/{id}")
    public ResponseEntity<String> deleteShowById(@PathVariable(value = "id") Integer showId) {

        return showService.deleteShow(showId);
    }

    @GetMapping("/show/theaterMovieDate")
    public ResponseEntity<List<String>> getTimingByTheaterIdMovieIdAndDate(@RequestParam Integer theaterId, @RequestParam Integer movieId, @RequestParam String date){
        return showService.getTimingByTheaterIdMovieIdAndDate(theaterId, movieId, date);
    }

//    @GetMapping("/show/theaterMovieDate")
//    public ResponseEntity<List<Show>> getShowsByTheaterIdMovieIdAndDate(@RequestParam Integer theaterId, @RequestParam Integer movieId, @RequestParam String date){
//        return showService.getShowsByTheaterIdMovieIdAndDate(theaterId, movieId, date);
//    }
}
