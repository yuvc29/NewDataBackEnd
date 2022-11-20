package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    Transaction findByTransactionId(Integer transactionId);
}
