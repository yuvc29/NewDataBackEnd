package fareye.BookMyMovie;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookMyMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookMyMovieApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
//	ModelMapper modelMapper = new ModelMapper();
}
