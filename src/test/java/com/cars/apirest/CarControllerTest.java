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
	
	@Test
	public void carTestGetAll() throws Exception {
		mockMvc.perform(get("/veiculos"))
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
}
