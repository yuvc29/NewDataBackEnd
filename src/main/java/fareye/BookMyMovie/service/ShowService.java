package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.ActorDto;
import fareye.BookMyMovie.dto.ShowDto;
import fareye.BookMyMovie.modal.Movie;
import fareye.BookMyMovie.modal.Orders;
import fareye.BookMyMovie.modal.Seat;
import fareye.BookMyMovie.modal.Show;
import fareye.BookMyMovie.reposatory.MovieRepo;
import fareye.BookMyMovie.reposatory.SeatRepo;
import fareye.BookMyMovie.reposatory.ShowRepo;
import fareye.BookMyMovie.reposatory.TheaterRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {
    @Autowired
    ShowRepo showRepo;
    @Autowired
    MovieRepo movieRepo;
    @Autowired
    TheaterRepo theaterRepo;
    @Autowired
    SeatRepo seatRepo;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<ShowDto> addShow(ShowDto showDto){
        Show show = modelMapper.map(showDto, Show.class);
        show.setMovie(movieRepo.findByMovieId(show.getMovieId()));
        show.setTheater(theaterRepo.findByTheaterId(show.getTheaterId()));
        Show newShow = showRepo.save(show);
        for(int i=1; i<=253; i++){
            Seat seat = new Seat();
            seat.setShow(newShow);
            seat.setShowId(newShow.getShowId());
            seat.setSeatNo(i);
            seat.setPrice(100);
            seatRepo.save(seat);
        }
        ShowDto dto = modelMapper.map(newShow, ShowDto.class);

        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<ShowDto>> getAllShow(){
        List<Show> shows = new ArrayList<Show>();
        showRepo.findAll().forEach(shows::add);
        if (shows.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<ShowDto> showDtos = shows.stream().map(show -> modelMapper.map(show,ShowDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(showDtos, HttpStatus.OK);
    }

    public ResponseEntity<ShowDto> updateShow(Integer showId, ShowDto showDto) {
        Show show = modelMapper.map(showDto, Show.class);
        show.setShowId(showId);
        Show updatedShow = showRepo.save(show);
        ShowDto dto = modelMapper.map(updatedShow, ShowDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteShow(Integer showId) {
        Show show = showRepo.findByShowId(showId);
        showRepo.delete(show);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<ShowDto> getShowById(Integer showId) {
        ShowDto dto = modelMapper.map(showRepo.findByShowId(showId), ShowDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<String>> getTimingByTheaterIdMovieIdAndDate(Integer theaterId, Integer movieId, String date){
        List<String> timings = showRepo.getTimingByTheaterIdMovieIdAndDate(theaterId, movieId, date);
        return ResponseEntity.ok(timings);
    }

//    public ResponseEntity<List<Show>> getShowsByTheaterIdMovieIdAndDate(Integer theaterId, Integer movieId, String date){
//        List<Show> shows = showRepo.getAllByTheaterIdAndMovieIdAndDate(theaterId, movieId, date);
//        return ResponseEntity.ok(shows);
//    }
}
