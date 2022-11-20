package fareye.BookMyMovie.controller;

import fareye.BookMyMovie.dto.CityDto;
import fareye.BookMyMovie.modal.City;
import fareye.BookMyMovie.modal.City;
import fareye.BookMyMovie.reposatory.CityRepo;
import fareye.BookMyMovie.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @PostMapping("/city")
    public ResponseEntity<String> addCity(@RequestBody CityDto city){
        return cityService.addCity(city);
    }

    @GetMapping("/city")
    public ResponseEntity<List<CityDto>> getAllCity(){
        return cityService.getAllCity();
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable(value = "id") Integer cityId) {
        return cityService.getCityById(cityId);
    }

    @PutMapping("/city/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable(value = "id") Integer cityId,
                                                 @RequestBody CityDto city) {
        return cityService.updateCity(cityId, city);
    }

    @DeleteMapping("/city/{id}")
    public ResponseEntity<String> deleteCity(@PathVariable(value = "id") Integer cityId) {
        return cityService.deleteCity(cityId);
    }
}
