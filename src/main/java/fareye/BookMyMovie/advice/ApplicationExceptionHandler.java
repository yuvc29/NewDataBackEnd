package fareye.BookMyMovie.advice;

import fareye.BookMyMovie.exceptions.ThereAreNoActorsException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleNotValidArguments(MethodArgumentNotValidException e){
        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleNotValidArguments(HttpRequestMethodNotSupportedException e){
        String error = new String();
        error = e.getMessage();
        return error;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String,String> handleNotValidArguments(ConstraintViolationException e){
        Map<String,String> errorMap = new HashMap<>();
        e.getConstraintViolations().forEach(error->{
            errorMap.put(error.getMessage(),error.getMessageTemplate());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleNotValidArguments(IllegalArgumentException e){
        String error = new String();
        error = e.getLocalizedMessage();
        return error;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException e) {

        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        ErrorMessage errorMessage = new ErrorMessage(message);
        return new ResponseEntity<>((errorMessage.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ThereAreNoActorsException.class)
    public String handleException(ThereAreNoActorsException e) {
        String error = new String();
        error = e.getLocalizedMessage();
        return error;
    }


}
