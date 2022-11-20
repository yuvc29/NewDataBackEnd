package fareye.BookMyMovie.finalController;

import fareye.BookMyMovie.dto.UsersDto;
import fareye.BookMyMovie.modal.*;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnAuthorisedControllerTest {
    RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8080/UNAUTHORISED";

    @Test
    void getAllCity(){
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/city")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void getCityById() {
        Integer id = 8;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/city/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<City> response = restTemplate.exchange(req, City.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllGenre() {
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/genre")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllMovies() throws URISyntaxException {
        RequestEntity<Void> req = RequestEntity.get(new URI(baseUrl+"/movie")).build();
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getMovieById(){
        Integer id = 3;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/movie/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<Movie> response = restTemplate.exchange(req, Movie.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllActorsByMovieId() {
        Integer id = 3;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/actors/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getSeatById() {
        Integer id = 57;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/seat/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<Seat> response = restTemplate.exchange(req, Seat.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findSeatNoByOrderId() {
        Integer id = 47;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/seat/order?orderId="+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllShow() {
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/show")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getShowById() {
        Integer id = 75;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/show/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<Show> response = restTemplate.exchange(req, Show.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
//------------------------------------------------------------------------------
    @Test
    void getTimingByTheaterIdMovieIdAndDate() {
        Integer theaterId=1;
        Integer movieId=2;
        String date="";
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/show/theaterMovieDate?theaterId="+theaterId+
            "&movieId="+movieId+"&date"+date)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllTheater() {
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/theater")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getTheaterById() {
        Integer id = 73;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/theater/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<Theater> response = restTemplate.exchange(req, Theater.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getTheaterByCityId() {
        Integer id = 57;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/seat/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<Theater> response = restTemplate.exchange(req, Theater.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getTheaterByCityIdMovieIdAndDate() {
        Integer cityId=1;
        Integer movieId=2;
        String date="";
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/theater/cityMovieDate?cityId="+cityId+
                    "&movieId="+movieId+"&date"+date)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<List> response = restTemplate.exchange(req, List.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createUser() {
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/user/4")).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<UsersDto> response = restTemplate.exchange(req, UsersDto.class);
        UsersDto users = response.getBody();
        users.setUserId(null);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<UsersDto> request = new HttpEntity<UsersDto>(users,headers);
        response = restTemplate.exchange(baseUrl+"/user", HttpMethod.POST, request, UsersDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getUsersByEmail() {
        String email="sajal@gmail.com";
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/user/email?email="+email)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<Users> response = restTemplate.exchange(req, Users.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getUserById() {
        Integer id = 4;
        RequestEntity<Void> req = null;
        try {
            req = RequestEntity.get(new URI(baseUrl+"/user/"+id)).build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<UsersDto> response = restTemplate.exchange(req, UsersDto.class);
//        Users users = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}