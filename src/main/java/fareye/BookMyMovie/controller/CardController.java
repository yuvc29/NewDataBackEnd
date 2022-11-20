package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.CardDto;
import fareye.BookMyMovie.modal.Card;
import fareye.BookMyMovie.modal.Users;
import fareye.BookMyMovie.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CardController {
    @Autowired
    CardService cardService;

//    @PostMapping("/card")
//    public ResponseEntity<Card> addCard(@RequestBody Card card){
//        return cardService.addCard(card);
//    }
    @PostMapping("/card")
    public ResponseEntity<CardDto> addCard(@RequestBody CardDto card){
        return cardService.addCard(card);
    }

    @GetMapping("/card")
    public List<CardDto> getAllCard(){
        return cardService.getAllCard();
    }

    @GetMapping("/card/{id}")
    public ResponseEntity<CardDto> getCardById(@PathVariable(value = "id") Integer cardId) {
        return cardService.getCardById(cardId);
    }

    @PutMapping("/card/{id}")
    public ResponseEntity<CardDto> updateCard(@PathVariable(value = "id") Integer cardId,
                                                 @RequestBody Card card) {
        return cardService.updateCard(cardId, card);
    }

    @DeleteMapping("/card/{id}")
    public ResponseEntity<String> deleteCard(@PathVariable(value = "id") Integer cardId) {
        return cardService.deleteCard(cardId);
    }

    @GetMapping("/card/get/{id}")
    public Users getUserByCardId(@PathVariable(value = "id") Integer cardId) {
        return cardService.getUserByCardId(cardId);
    }

    @GetMapping("/card/user/{id}")
    public ResponseEntity<List<CardDto>> getCardsByUserId(@PathVariable(value = "id") Integer userId){
        return cardService.getCardsByUserId(userId);
    }
}
