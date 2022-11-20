package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.OrdersDto;
import fareye.BookMyMovie.modal.Orders;
import fareye.BookMyMovie.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrdersService orderService;
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

    @PutMapping("/order/{id}")
    public ResponseEntity<OrdersDto> updateOrder(@PathVariable(value = "id") Integer orderId,
                                                 @RequestBody OrdersDto order) {
        return orderService.updateOrders(orderId, order);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(value = "id") Integer orderId) {
        return orderService.deleteOrders(orderId);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<String>> findOrdersByUserId(@RequestParam Integer userId){
        return orderService.findOrdersByUserId(userId);
    }
    @PostMapping("/order/seat")
    public ResponseEntity<OrdersDto> addOrderAndSeat(@RequestBody OrdersDto order, @RequestParam String seats){
        return orderService.addOrderAndSeat(order,seats);
    }
}
