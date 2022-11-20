package fareye.BookMyMovie.finalController;

import fareye.BookMyMovie.dto.*;
import fareye.BookMyMovie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ADMIN")
public class AdminController {
    @Autowired
    ActorService actorService;
    @Autowired
    CastService castService;
    @Autowired
    CityService cityService;
    @Autowired
    GenreService genreService;
    @Autowired
    GenreLinkService genreLinkService;
    @Autowired
    MovieService movieService;
    @Autowired
    SeatService seatService;
    @Autowired
    ShowService showService;
    @Autowired
    TheaterService theaterService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;

    @PostMapping("/actor")
    public ResponseEntity<String> addActor(@RequestBody ActorDto actor){
        return actorService.addActor(actor);
    }
    @PostMapping("/cast")
    public ResponseEntity<String> addMovieAndActors(@RequestParam Integer movieId, @RequestParam Integer actorId){
        return castService.addMovieAndActors(actorId, movieId);
    }
    @PostMapping("/city")
    public ResponseEntity<String> addCity(@RequestBody CityDto city){
        return cityService.addCity(city);
    }
    @PostMapping("/genre")
    public ResponseEntity<String> addGenre(@RequestBody GenreDto genre){
        return genreService.addGenre(genre);
    }
    @PutMapping("/genre/{id}")
    public ResponseEntity<GenreDto> updateGenre(@PathVariable(value = "id") Integer genreId,@RequestBody GenreDto genre) {
        return genreService.updateGenre(genreId, genre);
    }
    @DeleteMapping("/genre/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable(value = "id") Integer genreId) {
        return genreService.deleteGenre(genreId);
    }
    @PostMapping("/genreLink")
    public ResponseEntity<String> addMovieAndGenre(@RequestParam Integer movieId, @RequestParam Integer genreId){
        return genreLinkService.addMovieAndGenre(movieId, genreId);
    }
    @PostMapping("/movie")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movie){
        return movieService.addMovie(movie);
    }
    @PutMapping("/movie/{id}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable(value = "id") Integer movieId, @RequestBody MovieDto movie) {
        return movieService.updateMovie(movieId, movie);
    }
    @DeleteMapping("/movie/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable(value = "id") Integer movieId) {
        return movieService.deleteMovie(movieId);
    }
    @DeleteMapping("/seat/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable(value = "id") Integer seatId) {
        return seatService.deleteSeat(seatId);
    }
    @PostMapping("/show")
    public ResponseEntity<ShowDto> addShow(@RequestBody ShowDto show){
        return showService.addShow(show);
    }
    @PutMapping("/show/{id}")
    public ResponseEntity<ShowDto> updateShow(@PathVariable(value = "id") Integer showId,
                                              @RequestBody ShowDto show) {
        return showService.updateShow(showId, show);
    }
    @DeleteMapping("/show/{id}")
    public ResponseEntity<String> deleteShow(@PathVariable(value = "id") Integer showId) {
        return showService.deleteShow(showId);
    }
    @PostMapping("/theater")
    public ResponseEntity<TheaterDto> addTheater(@RequestBody TheaterDto theater){
        return theaterService.addTheater(theater);
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
    @GetMapping("/user")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable(value = "id") Integer userId) {
        return userService.getUserById(userId);
    }
    @PostMapping("/postGenre")
    public ResponseEntity<String> addGenreToMovie(@RequestBody Map<String,String> ids){
        Integer x = Integer.parseInt(ids.get("movieId"));
        String y =    ids.get("genres");
        return genreLinkService.addGenreToMovie(x,y);
    }
}
