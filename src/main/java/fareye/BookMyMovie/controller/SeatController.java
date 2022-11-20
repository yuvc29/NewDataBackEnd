package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.SeatDto;
import fareye.BookMyMovie.modal.Seat;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class SeatController {
    @Autowired
    SeatService seatService;

    @PostMapping("/seat")
    public ResponseEntity<String> addSeat(@RequestBody SeatDto seat){
        return seatService.addSeat(seat);
    }

    @GetMapping("/seat")
    public ResponseEntity<List<SeatDto>> getAllSeat(){
        return seatService.getAllSeat();
    }

    @GetMapping("/seat/{id}")
    public ResponseEntity<SeatDto> getSeatById(@PathVariable(value = "id") Integer seatId) {
        return seatService.getSeatById(seatId);
    }

    @PutMapping("/seat/{id}")
    public ResponseEntity<SeatDto> updateSeat(@PathVariable(value = "id") Integer seatId,
                                              @RequestBody SeatDto seat) {
        return seatService.updateSeat(seatId, seat);
    }

    @DeleteMapping("/seat/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable(value = "id") Integer seatId) {
        return seatService.deleteSeat(seatId);
    }

    @GetMapping("/seat/show/{id}")
    public ResponseEntity<List<SeatDto>> getSeatsByShowId(@PathVariable(value = "id") Integer showId){
        return seatService.getSeatsByShowId(showId);
    }

    @GetMapping("/seat/order")
    public ResponseEntity<List<Integer>> findSeatNoByOrderId(@RequestParam Integer orderId){
        return seatService.findSeatNoByOrderId(orderId);
    }
}
