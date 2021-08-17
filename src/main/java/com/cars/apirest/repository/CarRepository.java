package com.cars.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cars.apirest.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}