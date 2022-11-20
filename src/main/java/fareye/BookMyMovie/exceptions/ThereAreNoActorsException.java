package fareye.BookMyMovie.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No Actors found")  // 404
public class ThereAreNoActorsException extends Throwable {

    public ThereAreNoActorsException(String s){
        super(s);
    }
}
