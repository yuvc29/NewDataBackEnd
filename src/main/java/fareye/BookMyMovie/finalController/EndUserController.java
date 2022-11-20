package fareye.BookMyMovie.finalController;


import fareye.BookMyMovie.dto.*;
import fareye.BookMyMovie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/USER")
public class EndUserController {
    @Autowired
    CardService cardService;
    @Autowired
    OrdersService orderService;
    @Autowired
    SeatService seatService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    UserService userService;

    @PostMapping("/card")
    public ResponseEntity<CardDto> addCard(@RequestBody CardDto card){
        return cardService.addCard(card);
    }
    @GetMapping("/card/user/{id}")
    public ResponseEntity<List<CardDto>> getCardsByUserId(@PathVariable(value = "id") Integer userId){
        return cardService.getCardsByUserId(userId);
    }
    @PostMapping("/order")
    public ResponseEntity<OrdersDto> addOrder(@RequestBody OrdersDto order){
        return orderService.addOrders(order);
    }
    @GetMapping("/order")
    public ResponseEntity<List<OrdersDto>> getAllOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<OrdersDto> getOrderById(@PathVariable(value = "id") Integer orderId) {
        return orderService.getOrdersById(orderId);
    }
    @GetMapping("/order/user")
    public ResponseEntity<List<String>> findOrdersByUserId(@RequestParam Integer userId){
        return orderService.findOrdersByUserId(userId);
    }
    @PutMapping("/seat/{id}")
    public ResponseEntity<SeatDto> updateSeat(@PathVariable(value = "id") Integer seatId,@RequestBody SeatDto seat) {
        return seatService.updateSeat(seatId, seat);
    }
    @GetMapping("/seat/show/{id}")
    public ResponseEntity<List<SeatDto>> getSeatsByShowId(@PathVariable(value = "id") Integer showId){
        return seatService.getSeatsByShowId(showId);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Boolean> addTransaction(@RequestBody TransactionDto transaction){
        return transactionService.addTransaction(transaction);
    }
    @GetMapping("/transaction")
    public ResponseEntity<List<TransactionDto>> getAllTransaction(){
        return transactionService.getAllTransaction();
    }
    @GetMapping("/transaction/{id}")
    public ResponseEntity<TransactionDto> getTransactionById(@PathVariable(value = "id") Integer transactionId) {
        return transactionService.getTransactionById(transactionId);
    }
    @PostMapping("/updateseats")
    public ResponseEntity<String> updateSeatsAndTransaction(@RequestParam String details){
        return transactionService.updateSeatsAndTransaction(details);
    }
    @PutMapping("/user/{id}")
    public ResponseEntity<UsersDto> updateUser(@PathVariable(value = "id") Integer userId,@RequestBody UsersDto newUser) {
        return userService.updateEmployee(userId, newUser);
    }
    @PostMapping("/order/seat")
    public ResponseEntity<OrdersDto> addOrderAndSeat(@RequestBody OrdersDto order, @RequestParam String seats){
        return orderService.addOrderAndSeat(order,seats);
    }
}
