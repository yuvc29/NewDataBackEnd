package fareye.BookMyMovie.dto;

import lombok.Data;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
public class ActorDto {

    private Integer actorId;
    private String firstName;
    private String lastName;
    private String role;
    private String image;
}
