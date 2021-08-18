package com.cars.apirest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cars.apirest.models.Car;
import java.util.List;
import com.cars.apirest.repositories.CarRepository;

@RestController
public class CarResource {
    @Autowired
    CarRepository carRepository;

    @GetMapping("/veiculos")
    public List<Car> listCars() {
        return carRepository.findAll();
    }
    
    @PostMapping("/veiculos")
    public Car create(@RequestBody Car car) {
        return carRepository.save(car);
    }
}