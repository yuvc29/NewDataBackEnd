package fareye.BookMyMovie.finalController;

import fareye.BookMyMovie.dto.*;
import fareye.BookMyMovie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/UNAUTHORISED")
public class UnAuthorisedController {
    @Autowired
    ActorService actorService;
    @Autowired
    CityService cityService;
    @Autowired
    GenreService genreService;
    @Autowired
    MovieService movieService;
    @Autowired
    SeatService seatService;
    @Autowired
    ShowService showService;
    @Autowired
    TheaterService theaterService;
    @Autowired
    UserService userService;
    @GetMapping("/city")
    public ResponseEntity<List<CityDto>> getAllCity(){
        return cityService.getAllCity();
    }
    @GetMapping("/city/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable(value = "id") Integer cityId) {
        return cityService.getCityById(cityId);
    }
    @GetMapping("/genre")
    public ResponseEntity<List<GenreDto>> getAllGenre(){
        return genreService.getAllGenre();
    }
    @GetMapping("/movie")
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return movieService.getAllMovies();
    }
    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable(value = "id") Integer movieId) {
        return movieService.getMovieById(movieId);
    }
    @GetMapping("/actors/{movie_id}")
    public ResponseEntity<List<String>> getAllActorsByMovieId(@PathVariable(value = "movie_id") Integer movieId){
        return actorService.getActorsByMovieId(movieId);
    }
    @GetMapping("/seat/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable(value = "id") Integer seatId) {
        return seatService.getSeatById(seatId);
    }
    @GetMapping("/seat/order")
    public ResponseEntity<List<Integer>> findSeatNoByOrderId(@RequestParam Integer orderId){
        return seatService.findSeatNoByOrderId(orderId);
    }
    @GetMapping("/show")
    public ResponseEntity<List<ShowDto>> getAllShow(){
        return showService.getAllShow();
    }
    @GetMapping("/show/{id}")
    public ResponseEntity<ShowDto> getShowById(@PathVariable(value = "id") Integer showId) {
        return showService.getShowById(showId);
    }
    @GetMapping("/show/theaterMovieDate")
    public ResponseEntity<List<String>> getTimingByTheaterIdMovieIdAndDate(@RequestParam Integer theaterId, @RequestParam Integer movieId, @RequestParam String date){
        return showService.getTimingByTheaterIdMovieIdAndDate(theaterId, movieId, date);
    }
    @GetMapping("/theater")
    public ResponseEntity<List<TheaterDto>> getAllTheater(){
        return theaterService.getAllTheater();
    }
    @GetMapping("/theater/{id}")
    public ResponseEntity<TheaterDto> getTheaterById(@PathVariable(value = "id") Integer theaterId) {
        return theaterService.getTheaterById(theaterId);
    }
    @GetMapping("/theater/city/{id}")
    public ResponseEntity<List<TheaterDto>> getTheaterByCityId(@PathVariable(value = "id") Integer cityId) {
        return theaterService.getTheaterByCityId(cityId);
    }
    @GetMapping("/theater/cityMovieDate")
    public ResponseEntity<List<String>> getTheaterByCityIdMovieIdAndDate(@RequestParam Integer cityId, @RequestParam Integer movieId, @RequestParam String date){
        return theaterService.getTheaterByCityIdMovieIdAndDate(cityId, movieId, date);
    }
    @PostMapping("/user")
    public ResponseEntity<UsersDto> createUser(@RequestBody UsersDto user) {
        return userService.createUser(user);
    }
    @GetMapping("/user/email")
    public ResponseEntity<UsersDto> getUsersByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable(value = "id") Integer userId) {
        return userService.getUserById(userId);
    }
    @GetMapping("/genre/movie")
    public ResponseEntity<List<String>> getGenreByMovieId(@RequestParam Integer movieId){
        return genreService.getGenreByMovieId(movieId);
    }
}
