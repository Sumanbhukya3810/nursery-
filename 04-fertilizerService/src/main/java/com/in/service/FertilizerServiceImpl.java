package com.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in.entity.Fertilizer;
import com.in.entity.FertilizerType;
import com.in.exception.InvalidFertilizerException;
import com.in.repository.FertilizerRepository;

@Service
public class FertilizerServiceImpl implements FertilizerService {
	@Autowired
	FertilizerRepository FertilizerRepository;

	@Override
	public List<Fertilizer> getAllFertilizers() throws InvalidFertilizerException {

		if (FertilizerRepository.findAll().isEmpty())
			throw new InvalidFertilizerException("List is empty");
		else
			return FertilizerRepository.findAll();
	}

	@Override
	public Fertilizer getFertilizerById(long id) throws InvalidFertilizerException {
		Optional<Fertilizer> p = FertilizerRepository.findById(id);
		if (p.isPresent())
			return p.get();
		else
			throw new InvalidFertilizerException("Id not found");
	}

	@Override
	public Fertilizer saveFertilizer(Fertilizer p) throws InvalidFertilizerException {
		if (p.getFertilizerDescription() == null || p.getFertilizerDescription().equals("")) {
			throw new InvalidFertilizerException("Description is not valid.");
		}

		if (p.getFertilizerName() == null || p.getFertilizerName().equals("")) {
			throw new InvalidFertilizerException("Fertilizer name is not valid.");
		}
		if (p.getFertilizerPrice() == 0.0) {
			throw new InvalidFertilizerException("Fertilizer price is not valid.");

		}
		/*
		 * if(!p.getType().equals("Climber")||!p.getType().equals("Shrub")||!p.getType()
		 * .equals("Creeper")||!p.getType().equals("Herb")||!p.getType().equals("Tree"))
		 * { throw new
		 * InvalidFertilizerException("Type can be only climber,shrub,herb,tree,creeper"); }
		 */

		FertilizerRepository.save(p);
		return p;

	}

	public Fertilizer updateFertilizer(Fertilizer p, long id) throws InvalidFertilizerException {
		// Optional<Fertilizer> plan=FertilizerRepository.findById(id);
		Optional<Fertilizer> Fertilizer = FertilizerRepository.findById(id);
		if (Fertilizer.isEmpty())
			throw new InvalidFertilizerException("Id not found");
		else {
			Fertilizer.get().setFertilizerDescription(p.getFertilizerDescription());
			// Fertilizer.setFertilizerId(p.getFertilizerId());
			Fertilizer.get().setFertilizerName(p.getFertilizerName());
			Fertilizer.get().setFertilizerPrice(p.getFertilizerPrice());
			Fertilizer.get().setType(p.getType());
			FertilizerRepository.save(p);
			return Fertilizer.get();
		}
	}

	public Fertilizer deleteFertilizer(long id) throws InvalidFertilizerException {
		Optional<Fertilizer> p = FertilizerRepository.findById(id);
		if (p.isEmpty()) {
			throw new InvalidFertilizerException("Id not found");
		} else {
			FertilizerRepository.deleteById(id);
			return p.get();
		}
	}

	public List<Fertilizer> searchByType(FertilizerType type) throws InvalidFertilizerException {
		List<Fertilizer> Fertilizers = FertilizerRepository.findByType(type);

		if (Fertilizers.isEmpty()) {
			throw new InvalidFertilizerException("No Fertilizers found with the type: " + type);
		}

		return Fertilizers;
	}

}
