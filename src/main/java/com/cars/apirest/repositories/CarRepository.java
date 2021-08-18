package com.cars.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cars.apirest.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);
}