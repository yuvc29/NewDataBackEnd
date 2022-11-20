package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.TransactionDto;
import fareye.BookMyMovie.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    TransactionService transactionService;

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

    @PutMapping("/transaction/{id}")
    public ResponseEntity<TransactionDto> updateTransaction(@PathVariable(value = "id") Integer transactionId,
                                                 @RequestBody TransactionDto transaction) {
        return transactionService.updateTransaction(transactionId, transaction);
    }

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable(value = "id") Integer transactionId) {
        return transactionService.deleteTransaction(transactionId);
    }

    @PostMapping("/updateseats")
    public ResponseEntity<String> updateSeatsAndTransaction(@RequestParam String details){
        return transactionService.updateSeatsAndTransaction(details);
    }
}
