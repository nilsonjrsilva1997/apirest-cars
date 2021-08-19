package com.cars.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cars.apirest.models.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);
    
    @Query("SELECT COUNT(*) FROM Car WHERE sold = false")
    int countCarNotSold();
    
    // @Query("SELECT * FROM Car WHERE year >= 1000 AND year <= (1000 + 10)")
    // int carByYear(@Param("year") int year);
}