package fareye.BookMyMovie.service;

import fareye.BookMyMovie.dto.ActorDto;
import fareye.BookMyMovie.dto.CityDto;
import fareye.BookMyMovie.modal.City;
import fareye.BookMyMovie.modal.Theater;
import fareye.BookMyMovie.reposatory.CityRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    CityRepo cityRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseEntity<String> addCity(CityDto cityDto){
        City city = modelMapper.map(cityDto, City.class);
//        city.setTheaters(new ArrayList<Theater>());
        cityRepo.save(city);
        return ResponseEntity.ok("City added");
    }

    public ResponseEntity<List<CityDto>> getAllCity(){
        List<City> citys = new ArrayList<City>();
        cityRepo.findAll().forEach(citys::add);
        if (citys.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CityDto> cityDtos = citys.stream().map(city -> modelMapper.map(city,CityDto.class)).collect(Collectors.toList());
        return new ResponseEntity<>(cityDtos, HttpStatus.OK);
    }

    public ResponseEntity<CityDto> updateCity(Integer cityId, CityDto cityDto) {
        City city = modelMapper.map(cityDto, City.class);
        city.setCityId(cityId);
        City updatedCity = cityRepo.save(city);
        CityDto dto = modelMapper.map(updatedCity, CityDto.class);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<String> deleteCity(Integer cityId) {
        City city = cityRepo.findByCityId(cityId);
        cityRepo.delete(city);
        return ResponseEntity.ok("Deleted Successfully");
    }

    public ResponseEntity<CityDto> getCityById(Integer cityId) {
        CityDto dto = modelMapper.map(cityRepo.findByCityId(cityId), CityDto.class);
        return ResponseEntity.ok(dto);
    }


}
