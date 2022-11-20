package fareye.BookMyMovie.reposatory;

import fareye.BookMyMovie.modal.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepo extends JpaRepository<City, Integer> {

    City findByCityId(Integer cityId);
}
