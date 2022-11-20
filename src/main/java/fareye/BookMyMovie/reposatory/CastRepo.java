package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Casts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepo extends JpaRepository<Casts, Integer> {
}
