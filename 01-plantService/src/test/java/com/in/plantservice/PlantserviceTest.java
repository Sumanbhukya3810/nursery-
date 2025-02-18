package com.in.plantservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.in.entity.Plant;
import com.in.entity.PlantType;
import com.in.exception.InvalidPlantException;
import com.in.repository.PlantRepository;
import com.in.service.PlantService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class PlantServiceTest {

	@Autowired
	private PlantService plantService;

	@MockBean
	private PlantRepository plantRepository;

	@BeforeEach
	void setUp() {

		MockitoAnnotations.openMocks(this);
		Plant plant = Plant.builder().plantName("Dafodil").plantDescription("good plant").plantPrice(500.0)
				.type(PlantType.Climber).plantId(452L).build();

		Mockito.when(plantRepository.findById(452L)).thenReturn(Optional.of(plant));

	}

	@Test
	public void whenIdIsValidThenReturnPlant() throws InvalidPlantException {
		long id = 452L;
		Plant found = plantService.getPlantById(id);
		assertEquals(id, found.getPlantId());
	}

	@Test
	public void whenGetAllPlantsCalled_thenReturnAllPlants() throws InvalidPlantException {
		// Arrange
		Plant plant1 = Plant.builder().plantName("Rose").plantDescription("Beautiful flower").plantPrice(100.0)
				.type(PlantType.Climber).build();

		Plant plant2 = Plant.builder().plantName("Tulip").plantDescription("Spring flower").plantPrice(80.0)
				.type(PlantType.Climber)

				.build();

		List<Plant> allPlants = Arrays.asList(plant1, plant2);
		when(plantRepository.findAll()).thenReturn(allPlants);

		// Act
		List<Plant> result = plantService.getAllPlants();

		// Assert
		assertEquals(2, result.size());
		assertEquals("Rose", result.get(0).getPlantName());
		assertEquals("Tulip", result.get(1).getPlantName());
	}

	@Test
	public void whenSavePlantCalled_thenPlantShouldBeSaved() throws InvalidPlantException {
		// Arrange
		Plant plantToSave = Plant.builder().plantName("Rose").plantDescription("Beautiful flower").plantPrice(100.0)
				.type(PlantType.Climber).build();

		Plant savedPlant = Plant.builder().plantId(1L) // Assume the ID is assigned by the database
				.plantName("Rose").plantDescription("Beautiful flower").plantPrice(100.0).type(PlantType.Climber)
				.build();

		when(plantRepository.save(plantToSave)).thenReturn(savedPlant);

		// Act
		Plant result = plantService.savePlant(plantToSave);

		// Assert
		assertEquals(1L, result.getPlantId());
		assertEquals("Rose", result.getPlantName());
		assertEquals("Beautiful flower", result.getPlantDescription());
		assertEquals(100.0, result.getPlantPrice());

		// Verify that the save method was called once
		Mockito.verify(plantRepository).save(plantToSave);
	}
}
