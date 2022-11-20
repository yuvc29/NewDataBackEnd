package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer> {
    Card findByCardId(Integer cardId);

    List<Card> findByUserId(Integer userId);
}
