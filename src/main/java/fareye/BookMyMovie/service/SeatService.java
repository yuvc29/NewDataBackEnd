package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.SeatDto;
import fareye.BookMyMovie.modal.Seat;
import fareye.BookMyMovie.reposatory.CityRepo;
import fareye.BookMyMovie.reposatory.OrdersRepo;
import fareye.BookMyMovie.reposatory.SeatRepo;
import fareye.BookMyMovie.reposatory.SeatRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {
    @Autowired
    SeatRepo seatRepo;
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    private ModelMapper modelMapper;
    public ResponseEntity<String> addSeat(SeatDto seatDto){
        Seat seat = modelMapper.map(seatDto, Seat.class);
        seat.setOrders(ordersRepo.findByOrderId(seat.getOrderId()));
        seatRepo.save(seat);
        return ResponseEntity.ok("Seat added");
    }

    public ResponseEntity<List<SeatDto>> getAllSeat(){
        List<Seat> seats = new ArrayList<Seat>();
        seatRepo.findAll().forEach(seats::add);
        if (seats.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<SeatDto> seatDtos = seats.stream().map(seat -> modelMapper.map(seat,SeatDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(seatDtos, HttpStatus.OK);
    }

    public ResponseEntity<SeatDto> getSeatById(Integer seatId) {
        SeatDto dto = modelMapper.map(seatRepo.findBySeatId(seatId), SeatDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<SeatDto> updateSeat(Integer seatId, SeatDto seatDto) {
        Seat seat = modelMapper.map(seatDto, Seat.class);
        seat.setSeatId(seatId);
        Seat updatedSeat = seatRepo.save(seat);
        SeatDto dto = modelMapper.map(updatedSeat, SeatDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteSeat(Integer seatId) {
        Seat seat = seatRepo.findBySeatId(seatId);
        seatRepo.delete(seat);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<List<SeatDto>> getSeatsByShowId(Integer showId){
        List<Seat> seats = seatRepo.findByShowId(showId);
        List<SeatDto> seatDtos = seats.stream().map(seat -> modelMapper.map(seat,SeatDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(seatDtos);
    }

    public ResponseEntity<String> bookSeat(Integer orderId, List<Integer> seatNos, Integer showId){
        for(int i=0; i<seatNos.size(); i++){
            Seat seat=seatRepo.findByShowIdAndSeatNo(showId, seatNos.get(i));
            seat.setOrderId(orderId);
            seat.setOrders(ordersRepo.findByOrderId(orderId));
            seatRepo.save(seat);
        }
        return ResponseEntity.ok("Booked");
    }

    public ResponseEntity<List<Integer>> findSeatNoByOrderId(Integer orderId){
        List<Seat> seats = seatRepo.findByOrderId(orderId);
        List<Integer> seatNo = seats.stream().map(seat -> seat.getSeatNo()).collect(Collectors.toList());
        return ResponseEntity.ok(seatNo);
    }

    public void reserveSeat(List<Integer> seatNos, Integer showId){
        for(int i=0; i<seatNos.size(); i++){
            Seat seat=seatRepo.findByShowIdAndSeatNo(showId, seatNos.get(i));
            if(seat.getOrderId()<0){
                seat.setOrderId(null);
            }
            seatRepo.save(seat);
        }
    }
}
