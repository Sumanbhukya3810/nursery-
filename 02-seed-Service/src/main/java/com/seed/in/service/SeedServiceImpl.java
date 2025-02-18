package com.seed.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seed.in.dto.request.SeedDto;
import com.seed.in.entity.Seed;
import com.seed.in.exception.ApplicationException;
import com.seed.in.repository.SeedRepository;

@Service
public class SeedServiceImpl implements SeedService {

	@Autowired
	private SeedRepository seedRepo;

	@Override
	public Seed addSeedName(SeedDto seed) {
		// TODO Auto-generated method stub
		Seed s = new Seed();
		BeanUtils.copyProperties(seed, s);
		return seedRepo.save(s);
	}

	@Override
	public List<Seed> allSeeds() {
		// TODO Auto-generated method stub
		return seedRepo.findAll();
	}

//	@Override
//	public Seed getSeedByPrice(float price) {
//		// TODO Auto-generated method stub
//		List<Seed> seedList = allSeeds();
//	    for (Seed seed : seedList) {
//	        if (seed.getPrice() == price) {
//	            return seed;
//	        }
//	    }
//		return null;
//	}

	@Override
	public Seed addDescription(Seed description) {
		// TODO Auto-generated method stub
		return seedRepo.save(description);
	}

	@Override
	public void deleteSeedById(int seedId) {
		// TODO Auto-generated method stub
		seedRepo.deleteById(seedId);
	}

	@Override
	public Seed updateSeed(int id, SeedDto seed) {
		// TODO Auto-generated method stub
		seedRepo.findById(id).orElseThrow(() -> new ApplicationException("Seed not found"));
		Seed s = new Seed();
		BeanUtils.copyProperties(seed, s);
		return seedRepo.save(s); 
	}

	@Override
	public Seed getSeedById(int id) {
		// TODO Auto-generated method stub
		Optional<Seed> opt = seedRepo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	
}
