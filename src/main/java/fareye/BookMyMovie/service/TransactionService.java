package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.TransactionDto;
import fareye.BookMyMovie.modal.Orders;
import fareye.BookMyMovie.modal.Show;
import fareye.BookMyMovie.modal.Transaction;
import fareye.BookMyMovie.reposatory.OrdersRepo;
import fareye.BookMyMovie.reposatory.TransactionRepo;
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
public class TransactionService {
    @Autowired
    TransactionRepo transactionRepo;
    @Autowired
    OrdersRepo ordersRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    SeatService seatService;

    public ResponseEntity<Boolean> addTransaction(TransactionDto transactionDto){
        transactionDto.setStatus(true);
//        if(transactionDto.getStatus()==true){
//
//        }
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        Orders orders = ordersRepo.findByOrderId(transaction.getOrderId());
//        orders.setStatus(true);
        transaction.setOrder(orders);
        transactionRepo.save(transaction);
        return ResponseEntity.ok(transaction.getStatus());
    }

    public ResponseEntity<List<TransactionDto>> getAllTransaction(){
        List<Transaction> transactions = new ArrayList<Transaction>();
        transactionRepo.findAll().forEach(transactions::add);
        if (transactions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<TransactionDto> transactionDtos = transactions.stream().map(transaction -> modelMapper.map(transaction,TransactionDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    public ResponseEntity<TransactionDto> getTransactionById(Integer transactionId) {
        TransactionDto dto = modelMapper.map(transactionRepo.findByTransactionId(transactionId), TransactionDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<TransactionDto> updateTransaction(Integer transactionId, TransactionDto transactionDto) {
        Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
        transaction.setTransactionId(transactionId);
        Transaction updatedTransaction = transactionRepo.save(transaction);
        TransactionDto dto = modelMapper.map(updatedTransaction, TransactionDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteTransaction(Integer transactionId) {
        Transaction transaction = transactionRepo.findByTransactionId(transactionId);
        transactionRepo.delete(transaction);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<String> updateSeatsAndTransaction(String details) {
        List<String> items = Arrays.asList(details.split("\\s*,\\s*"));
        List<Integer> integerList = items.stream()
                .map(Integer::valueOf).collect(Collectors.toList());
        Integer showId =integerList.get(0);
        Integer orderId =integerList.get(1);

        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setOrderId(orderId);
        addTransaction(transactionDto);

        List<Integer> seats = new ArrayList<>();
        for(int i= 2;i<integerList.size();i++){
            seats.add(integerList.get(i));
        }
        seatService.bookSeat(orderId,seats,showId);
        return ResponseEntity.ok("Transaction completed");

    }
}
