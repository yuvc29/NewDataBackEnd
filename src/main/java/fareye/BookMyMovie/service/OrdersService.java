package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.OrdersDto;
import fareye.BookMyMovie.modal.Orders;
import fareye.BookMyMovie.reposatory.CouponRepo;
import fareye.BookMyMovie.reposatory.OrdersRepo;
import fareye.BookMyMovie.reposatory.ShowRepo;
import fareye.BookMyMovie.reposatory.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Component
public class OrdersService {
    @Autowired
    OrdersRepo orderRepo;
    @Autowired
    ShowRepo showRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    CouponRepo couponRepo;
    @Autowired
    SeatService seatService;
    @Autowired
    private ModelMapper modelMapper;

    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
    public ResponseEntity<OrdersDto> addOrderAndSeat(OrdersDto orderDto, String seats){
        List<String> items = Arrays.asList(seats.split("\\s*,\\s*"));
        List<Integer> seat = items.stream()
                .map(Integer::valueOf).collect(Collectors.toList());
        seatService.bookSeat(-1,seat,orderDto.getShowId());
        setTimeout(()->seatService.reserveSeat(seat,orderDto.getShowId()), 50000);
        return addOrders(orderDto);
    }
    public ResponseEntity<List<OrdersDto>> getAllOrders(){
        List<Orders> orders = new ArrayList<>();
        orderRepo.findAll().forEach(orders::add);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<OrdersDto> orderDtos = orders.stream().map(order -> modelMapper.map(order,OrdersDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }
    public ResponseEntity<OrdersDto> getOrdersById(Integer orderId) {
        OrdersDto dto = modelMapper.map(orderRepo.findByOrderId(orderId), OrdersDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<OrdersDto> updateOrders(Integer orderId, OrdersDto orderDto) {
        Orders order = modelMapper.map(orderDto, Orders.class);
        order.setOrderId(orderId);
        Orders updatedOrders = orderRepo.save(order);
        OrdersDto dto = modelMapper.map(updatedOrders, OrdersDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<String> deleteOrders(Integer orderId) {
        Orders order = orderRepo.findByOrderId(orderId);
        orderRepo.delete(order);
        return ResponseEntity.ok("Deleted Successfully");
    }
    public ResponseEntity<List<String>> findOrdersByUserId(Integer userId){
        List<String> string = orderRepo.findOrdersByUserId(userId);
        return ResponseEntity.ok(string);
    }
    public ResponseEntity<OrdersDto> addOrders(OrdersDto orderDto){
        orderDto.setStatus(false);
        Orders order = modelMapper.map(orderDto, Orders.class);
        order.setShow(showRepo.findByShowId(order.getShowId()));
        order.setUsers(userRepo.findByUserId(order.getUserId()));
        order.setCoupon(couponRepo.findByCouponId(order.getCouponId()));
        Orders newOrder = orderRepo.save(order);
        OrdersDto dto = modelMapper.map(newOrder, OrdersDto.class);
        setTimeout(()->reserveSeat(newOrder), 300000);
        return ResponseEntity.ok(dto);
    }
    private void reserveSeat(Orders orders) {
        if(orders.getStatus()==false){
            deleteOrders(orders.getOrderId());
        }
    }
}
