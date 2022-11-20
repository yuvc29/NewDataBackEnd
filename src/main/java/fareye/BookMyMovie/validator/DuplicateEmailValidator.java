package fareye.BookMyMovie.validator;

import fareye.BookMyMovie.dto.UsersDto;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.reposatory.UserRepo;
import fareye.BookMyMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;


@Component
public class DuplicateEmailValidator implements ConstraintValidator<DuplicateEmail, Users> {
//        @Autowired
//        Users User;
//
//        @Autowired
//        UsersDto usersDto;
        @Autowired
        UserService userService;

        @Autowired
        public UserRepo userRepo;

        @Override
        public void initialize(DuplicateEmail constraintAnnotation) {
            ConstraintValidator.super.initialize(constraintAnnotation);
        }

    @Override


        public boolean isValid(Users user, ConstraintValidatorContext constraintValidatorContext){
            if(ifUserWithEmailExists(user)) return false;
        return true;
//            List<Users> list = userService.getAllUsersForValidations();
//            if(list!=null && list.size()!=0){
//                for (Users u:list){
//                    if (u.getEmail().equals(user.getEmail()))
//                        return true;
//                }
//            }
//            return false;
        }

    private boolean ifUserWithEmailExists(Users user) {
        List<Users> users1 = new ArrayList<Users>();
        String email = user.getEmail();
        List<Users> list = new ArrayList<Users>();
        Users users = userRepo.findByEmail(email);
        list = userRepo.findAll();
//        for(Users users :list){
//            if(users.getEmail().equals(email)){
//                return true;
//            }
//        }
        return false;
    }
}