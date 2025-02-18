package com.planter.in.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.planter.in.dto.request.PlanterDto;
import com.planter.in.entity.Planter;
import com.planter.in.service.PlanterService;


@RestController
@RequestMapping("/planters")
public class PlanterController {

	@Autowired
	private PlanterService planterService;
	
	Logger log = LoggerFactory.getLogger(PlanterController.class);
	
	@GetMapping("/test")
	public String test() {
		return "Hello";
	}
	
	@PostMapping
	public ResponseEntity<Planter> addPlanter(@RequestBody PlanterDto planter) {
		System.out.println(planter);
		planterService.addPlanter(planter);
		return new ResponseEntity<Planter>(HttpStatusCode.valueOf(201));
	}
	
	@GetMapping("/allplanters")
	public ResponseEntity<List<Planter>> getAllPlanters() {
		List<Planter> plant = planterService.allPlanters();
		if (plant.size() == 0) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(204));
		}
		return new ResponseEntity<>(plant, HttpStatusCode.valueOf(200));
	}
	
	// url = http://localhost:8080/planters/planter/id?id=
	@DeleteMapping("/planter/{id}")
	public ResponseEntity<Void> deletePlanter(@RequestParam("id") int planterId) {
		planterService.deletePlanterById(planterId);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Planter> updatePlanter(@PathVariable int id, @RequestBody PlanterDto planter) {
		Planter updatedPlanter = planterService.updatePlanter(id, planter);
		return new ResponseEntity<Planter>(HttpStatusCode.valueOf(201));
	}
	
	 @GetMapping("/price/{price}")
	    public ResponseEntity<Planter> getPlanterByPrice(@RequestParam("price") float price) {
	        Planter planter = planterService.getPlanterByPrice(price);
	        if (planter != null) {
	            return new ResponseEntity<>(planter, HttpStatusCode.valueOf(200));
	        } else {
	            return new ResponseEntity<>(HttpStatusCode.valueOf(204));
	        }
	    }

}
