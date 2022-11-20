package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.TheaterDto;
import fareye.BookMyMovie.dto.UsersDto;
import fareye.BookMyMovie.modal.Card;
import fareye.BookMyMovie.modal.Orders;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.reposatory.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepo userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<List<UsersDto>> getAllUsers() {
        List<Users> users = new ArrayList<Users>();
        userRepository.findAll().forEach(users::add);
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<UsersDto> usersDtos = users.stream().map(users1 -> modelMapper.map(users1,UsersDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(usersDtos, HttpStatus.OK);
    }
    public List<UsersDto> getAllUsersForValidations() {
        List<Users> users = new ArrayList<Users>();
        userRepository.findAll().forEach(users::add);
        if (users.isEmpty()) {
            return null;
        }
        List<UsersDto> usersDtos = users.stream().map(users1 -> modelMapper.map(users1,UsersDto.class)).collect(Collectors.toList());
//        List<Users> users = new ArrayList<>();
//        users = userRepository.findAll();
        return usersDtos;
    }

    public ResponseEntity<UsersDto> getUserById(Integer userId) {
        UsersDto dto = modelMapper.map(userRepository.findByUserId(userId), UsersDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<UsersDto> createUser(UsersDto userDto) {
        userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10)));
        Users user =  modelMapper.map(userDto, Users.class);
//        user.setOrders(new ArrayList<Orders>());
//        user.setCards(new ArrayList<Card>());
        Users newUser = userRepository.save(user);
        UsersDto newDto = modelMapper.map(newUser, UsersDto.class);
        return ResponseEntity.ok(newDto);
    }

    public ResponseEntity<UsersDto> updateEmployee(Integer userId, UsersDto usersDto) {
        Users user =  modelMapper.map(usersDto, Users.class);
        user.setUserId(userId);
        Users updatedUser = userRepository.save(user);
        UsersDto dto = modelMapper.map(updatedUser, UsersDto.class);
        return ResponseEntity.ok(dto);
    }
    public ResponseEntity<String> deleteUser(Integer userId) {
        Users users = userRepository.findByUserId(userId);
        userRepository.delete(users);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<UsersDto> getUserByEmail(String email){
        UsersDto dto = modelMapper.map(userRepository.findByEmail(email), UsersDto.class);
        return ResponseEntity.ok(dto);
    }
}
