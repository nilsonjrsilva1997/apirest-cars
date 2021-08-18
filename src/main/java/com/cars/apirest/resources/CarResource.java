package com.cars.apirest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cars.apirest.models.Car;

import java.util.ArrayList;
import java.util.List;
import com.cars.apirest.repositories.CarRepository;
import com.cars.apirest.validators.CarValidator;

@RestController
public class CarResource {
    @Autowired
    CarRepository carRepository;

    @GetMapping("/veiculos")
    public List<Car> listCars() {
        return carRepository.findAll();
    }
    
    @PostMapping("/veiculos")
    public ResponseEntity<Object> create(@RequestBody Car car) {
    	
    	CarValidator validator = new CarValidator(car);
    	
    	List<String> errors = validator.validate();
    	
    	if(!errors.isEmpty()) {
    		ResponseEntity<Object> carResponseEntity = new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    		return carResponseEntity;
    	} else {    		
    		car = carRepository.save(car);
    		ResponseEntity<Object> carResponseEntity = new ResponseEntity<>(car, HttpStatus.ACCEPTED);
    		return carResponseEntity;
    	}    	
    }
}