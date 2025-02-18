package com.seed.in.controller;

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

import com.seed.in.dto.request.SeedDto;
import com.seed.in.entity.Seed;
import com.seed.in.exception.ApplicationException;
import com.seed.in.service.SeedService;

@RestController
@RequestMapping("/seeds")
public class SeedController {

	@Autowired
	private SeedService seedService;

	Logger log = LoggerFactory.getLogger(SeedController.class);

	@GetMapping("/test")
	public String test() {
		return "Hello";
	}
	
	
	@PostMapping
	public ResponseEntity<Seed> addSeed(@RequestBody SeedDto seed) {
		System.out.println(seed);
		seedService.addSeedName(seed);
		return new ResponseEntity<Seed>(HttpStatusCode.valueOf(201));
	}
	

	@GetMapping("/allseeds")
	public ResponseEntity<List<Seed>> getAllSeeds() {
		List<Seed> seeds = seedService.allSeeds();
		if (seeds.size() == 0) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(204));
		}
		return new ResponseEntity<>(seeds, HttpStatusCode.valueOf(200));
	}
	
	
	@PostMapping("/description")
	public ResponseEntity<Seed> addDescription(@RequestBody Seed description) {
		Seed savedDescription = seedService.addDescription(description);
		return new ResponseEntity<>(savedDescription, HttpStatusCode.valueOf(201));
	}


	// url = http://localhost:8080/seeds/seed/id?id=
	@DeleteMapping("/seed/{id}")
	public ResponseEntity<Void> deleteSeed(@RequestParam("id") int seedId) {
		seedService.deleteSeedById(seedId);
		return ResponseEntity.ok().build();
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Seed> updateSeed(@PathVariable int id, @RequestBody SeedDto seed) {
		Seed updatedSeed = seedService.updateSeed(id, seed);
		return new ResponseEntity<Seed>(HttpStatusCode.valueOf(201));
	}


	@GetMapping("/{id}/seeds-per-packet")
	public ResponseEntity<Integer> getSeedsPerPacket(@PathVariable int id) {
		Seed seed = seedService.getSeedById(id);
		if (seed == null) {
			throw new ApplicationException("Seed not found with id: " + id);
		}
		return ResponseEntity.ok(seed.getNoOfSeedsPerPacket());
	}

}
