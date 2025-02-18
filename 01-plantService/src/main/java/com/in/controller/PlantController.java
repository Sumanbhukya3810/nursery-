package com.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.entity.Plant;
import com.in.entity.PlantType;
import com.in.exception.InvalidPlantException;
import com.in.service.PlantService;

@RestController
@RequestMapping("/plants")
public class PlantController {
@Autowired
PlantService plantService;
@GetMapping
public ResponseEntity<List<Plant>> getAllPlants()throws InvalidPlantException{

		return new ResponseEntity<>(plantService.getAllPlants(),HttpStatusCode.valueOf(200));
		
	
}
@GetMapping("/{id}")
public ResponseEntity<Plant>getPlantById(@PathVariable("id") long id) throws InvalidPlantException{
	Plant plant=plantService.getPlantById(id);
		return new ResponseEntity<>(plant,HttpStatusCode.valueOf(200));
}
@PostMapping
public ResponseEntity<Plant> savePlant(@RequestBody Plant p)throws InvalidPlantException {
    Plant savedPlant = plantService.savePlant(p);
    return new ResponseEntity<>(savedPlant, HttpStatusCode.valueOf(201)); // Use HttpStatus.CREATED
}
@PutMapping("/{id}")
public ResponseEntity<Plant> updatePlant(@RequestBody Plant p ,@PathVariable("id") long id)throws InvalidPlantException

{
	return  new ResponseEntity<>(plantService.updatePlant(p,id),HttpStatus.valueOf(200));
}
@DeleteMapping("/{id}")
public ResponseEntity<Plant> deletePlant(@PathVariable("id") long id)throws InvalidPlantException

{
	return  new ResponseEntity<>(plantService.deletePlant(id),HttpStatus.valueOf(200));
}


@GetMapping("/type/{type}")
public ResponseEntity<List<Plant>> getPlantByType(@PathVariable("type") PlantType type) throws InvalidPlantException {
	List<Plant> plant = plantService.searchByType(type);
	return new ResponseEntity<List<Plant>>(plant,HttpStatus.OK);
}
}




