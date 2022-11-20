package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<Users, Integer> {

    Users findByEmail(String email);

    Users findByUserId(Integer userId);

    List<Users> findAllByEmail(String email);
}
