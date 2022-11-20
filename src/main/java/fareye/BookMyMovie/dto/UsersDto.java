package fareye.BookMyMovie.dto;

import fareye.BookMyMovie.validator.DuplicateEmail;
import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data

public class UsersDto {

    private Integer userId;

    @NotNull(message = "First Name should not be null")
    private String firstName;
    @NotNull(message = "Last Name should not be null")
    private String lastName;
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"  , message = "Invalid Email entered")

//    @DuplicateEmail(message = "DuplicateEmail annotation fails")
    private String email;
//    @NotNull
    private String gender;
//    @NotNull
    private String dob;
    @NotNull(message = "Password should not be null")
    private String password;
    @NotBlank(message = "role should not be blank")
    private String role;
}
