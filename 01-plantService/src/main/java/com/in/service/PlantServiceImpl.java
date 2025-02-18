package com.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.entity.Plant;
import com.in.entity.PlantType;
import com.in.exception.InvalidPlantException;
import com.in.repository.PlantRepository;

@Service
public class PlantServiceImpl implements PlantService {
	@Autowired
	PlantRepository plantRepository;

	@Override
	public List<Plant> getAllPlants() throws InvalidPlantException {

		if (plantRepository.findAll().isEmpty())
			throw new InvalidPlantException("List is empty");
		else
			return plantRepository.findAll();
	}

	@Override
	public Plant getPlantById(long id) throws InvalidPlantException {
		Optional<Plant> p = plantRepository.findById(id);
		if (p.isPresent())
			return p.get();
		else
			throw new InvalidPlantException("Id not found");
	}

	@Override
	public Plant savePlant(Plant p) throws InvalidPlantException {
		if (p.getPlantDescription() == null || p.getPlantDescription().equals("")) {
			throw new InvalidPlantException("Description is not valid.");
		}

		if (p.getPlantName() == null || p.getPlantName().equals("")) {
			throw new InvalidPlantException("Plant name is not valid.");
		}
		if (p.getPlantPrice() == 0.0) {
			throw new InvalidPlantException("Plant price is not valid.");

		}
		/*
		 * if(!p.getType().equals("Climber")||!p.getType().equals("Shrub")||!p.getType()
		 * .equals("Creeper")||!p.getType().equals("Herb")||!p.getType().equals("Tree"))
		 * { throw new
		 * InvalidPlantException("Type can be only climber,shrub,herb,tree,creeper"); }
		 */

		plantRepository.save(p);
		return p;

	}

	public Plant updatePlant(Plant p, long id) throws InvalidPlantException {
		// Optional<Plant> plan=plantRepository.findById(id);
		Optional<Plant> plant = plantRepository.findById(id);
		if (plant.isEmpty())
			throw new InvalidPlantException("Id not found");
		else {
			plant.get().setPlantDescription(p.getPlantDescription());
			// plant.setPlantId(p.getPlantId());
			plant.get().setPlantName(p.getPlantName());
			plant.get().setPlantPrice(p.getPlantPrice());
			plant.get().setType(p.getType());
			plantRepository.save(p);
			return plant.get();
		}
	}

	public Plant deletePlant(long id) throws InvalidPlantException {
		Optional<Plant> p = plantRepository.findById(id);
		if (p.isEmpty()) {
			throw new InvalidPlantException("Id not found");
		} else {
			plantRepository.deleteById(id);
			return p.get();
		}
	}

	public List<Plant> searchByType(PlantType type) throws InvalidPlantException {
		List<Plant> plants = plantRepository.findByType(type);

		if (plants.isEmpty()) {
			throw new InvalidPlantException("No plants found with the type: " + type);
		}

		return plants;
	}

}
