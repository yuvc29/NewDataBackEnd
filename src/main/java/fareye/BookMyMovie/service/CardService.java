package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.CardDto;
import fareye.BookMyMovie.modal.Card;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.reposatory.CardRepo;
import fareye.BookMyMovie.reposatory.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {
    @Autowired
    CardRepo cardRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<CardDto> addCard(CardDto cardDto) {
        Card card = modelMapper.map(cardDto, Card.class);
        Users users = userRepo.findByUserId(card.getUserId());
        //UserNotFound Exception
        card.setUser(users);
        Card newCard = cardRepo.save(card);
        CardDto dto = modelMapper.map(newCard, CardDto.class);
        return ResponseEntity.ok(dto);
    }
    public List<CardDto> getAllCard(){
        List<Card> cards = new ArrayList<Card>();
        cardRepo.findAll().forEach(cards::add);
        List<CardDto> cardDtos = cards.stream().map(card -> modelMapper.map(card,CardDto.class)).collect(Collectors.toList());
        return cardDtos;
    }

    public ResponseEntity<CardDto> getCardById(Integer cardId) {
        CardDto dao = modelMapper.map(cardRepo.findByCardId(cardId), CardDto.class);
        return ResponseEntity.ok(dao);
    }
    public ResponseEntity<CardDto> updateCard(Integer cardId, Card card) {
        card.setCardId(cardId);
        Card updatedCard = cardRepo.save(card);
        CardDto dao = modelMapper.map(updatedCard,CardDto.class);
        return ResponseEntity.ok(dao);
    }

    public ResponseEntity<String> deleteCard(Integer cardId) {
        Card card = cardRepo.findByCardId(cardId);
        cardRepo.delete(card);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public Users getUserByCardId(Integer cardId){
        Card card = cardRepo.findByCardId(cardId);
        Users user = card.getUser();
        return user;
    }
    public ResponseEntity<List<CardDto>> getCardsByUserId(Integer userId){
        List<Card> cards = cardRepo.findByUserId(userId);
        List<CardDto> cardDtos = cards.stream().map(card -> modelMapper.map(card,CardDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(cardDtos);
    }

}
