package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.UsersDto;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.reposatory.UserRepo;
import fareye.BookMyMovie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user")
    public ResponseEntity<List<UsersDto>> getAllUsers() {
        return userService.getAllUsers();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UsersDto> getUserById(@PathVariable(value = "id") Integer userId) {
        return userService.getUserById(userId);
    }
    @PostMapping("/user")
    public ResponseEntity<UsersDto> createUser(@RequestBody @Valid UsersDto user) {
        return userService.createUser(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UsersDto> updateUser(@PathVariable(value = "id") Integer userId,
                                               @RequestBody UsersDto newUser) {
        return userService.updateEmployee(userId, newUser);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Integer userId) {
        return userService.deleteUser(userId);
    }
    @GetMapping("/user/email")
    public ResponseEntity<UsersDto> getUsersByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}
