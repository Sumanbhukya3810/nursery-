package com.in.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in.entity.Fertilizer;
import com.in.entity.FertilizerType;
import com.in.exception.InvalidFertilizerException;
import com.in.service.FertilizerService;


@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
@Autowired
FertilizerService fertilizerService;
@GetMapping
public ResponseEntity<List<Fertilizer>> getAllFertilizers()throws InvalidFertilizerException{

		return new ResponseEntity<>(fertilizerService.getAllFertilizers(),HttpStatusCode.valueOf(200));
		
	
}
@GetMapping("/{id}")
public ResponseEntity<Fertilizer>getFertilizerById(@PathVariable("id") long id) throws InvalidFertilizerException{
	Fertilizer fertilizer=fertilizerService.getFertilizerById(id);
		return new ResponseEntity<>(fertilizer,HttpStatusCode.valueOf(200));
}
@PostMapping
public ResponseEntity<Fertilizer> saveFertilizer(@RequestBody Fertilizer p)throws InvalidFertilizerException {
    Fertilizer savedfertilizer = fertilizerService.saveFertilizer(p);
    return new ResponseEntity<>(savedfertilizer, HttpStatusCode.valueOf(201)); // Use HttpStatus.CREATED
}
@PutMapping("/{id}")
public ResponseEntity<Fertilizer> updateFertilizer(@RequestBody Fertilizer p ,@PathVariable("id") long id)throws InvalidFertilizerException

{
	return  new ResponseEntity<>(fertilizerService.updateFertilizer(p,id),HttpStatus.valueOf(200));
}
@DeleteMapping("/{id}")
public ResponseEntity<Fertilizer> deleteFertilizer(@PathVariable("id") long id)throws InvalidFertilizerException 

{
	return  new ResponseEntity<>(fertilizerService.deleteFertilizer(id),HttpStatus.valueOf(200));
}


@GetMapping("/type/{type}")
public ResponseEntity<List<Fertilizer>> getFertilizerByType(@PathVariable("type") FertilizerType type) throws InvalidFertilizerException{
	List<Fertilizer> fertilizer = fertilizerService.searchByType(type);
	return new ResponseEntity<List<Fertilizer>>(fertilizer,HttpStatus.OK);
}
}




