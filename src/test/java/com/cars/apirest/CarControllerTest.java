package com.cars.apirest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.cars.apirest.models.Car;
import com.cars.apirest.repositories.CarRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.awt.PageAttributes.MediaType;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objMapper;
	
	@Autowired
    CarRepository carRepository;
	
	@Test
	public void carTestGetAll() throws Exception {
		mockMvc.perform(get("/veiculos"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void carTestCountNotSold() throws Exception {
		mockMvc.perform(get("/veiculos/nao-vendido"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void carTestCountByBrand() throws Exception {
		mockMvc.perform(get("/veiculos/por-marca/Fiat"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void carTestCountByDec() throws Exception {
		mockMvc.perform(get("/veiculos/por-decada/1990"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void carTestGetById() throws Exception {
		Car car = new Car();
		
		car.setBrand("Fiat");
		car.setDescription("Fiat uno antigo");
		car.setSold(false);
		car.setVehicle("uno");
		car.setYear(1996);
		
		Car newCar = carRepository.save(car);
		
		mockMvc.perform(get("/veiculos/" + newCar.getId()))
				.andExpect(status().isOk());
	}
	
	@Test
	public void carTestSave() throws Exception {
		Car car = new Car();
		
		car.setBrand("Fiat");
		car.setDescription("Fiat uno antigo");
		car.setSold(false);
		car.setVehicle("uno");
		car.setYear(1996);
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/veiculos")
			      .content(objMapper.writeValueAsString(car))
			      .contentType("application/json")
			      .accept("application/json"))
				  .andExpect(status().is(202));
	}
	
	@Test
	public void carTestUpdate() throws Exception {
		Car car = new Car();
		
		car.setBrand("Fiat");
		car.setDescription("Fiat uno antigo");
		car.setSold(false);
		car.setVehicle("uno");
		car.setYear(1996);
		
		Car newCar = carRepository.save(car);
		
		mockMvc.perform( MockMvcRequestBuilders
			      .put("/veiculos/" + newCar.getId())
			      .content(objMapper.writeValueAsString(car))
			      .contentType("application/json")
			      .accept("application/json"))
				  .andExpect(status().is(200));
	}
	
	@Test
	public void carTestDelete() throws Exception {
		Car car = new Car();
		
		car.setBrand("Fiat");
		car.setDescription("Fiat uno antigo");
		car.setSold(false);
		car.setVehicle("uno");
		car.setYear(1996);
		
		Car newCar = carRepository.save(car);
		
		mockMvc.perform( MockMvcRequestBuilders
			      .delete("/veiculos/" + newCar.getId())
			      .content(objMapper.writeValueAsString(car))
			      .contentType("application/json")
			      .accept("application/json"))
				  .andExpect(status().is(200));
	}
}
