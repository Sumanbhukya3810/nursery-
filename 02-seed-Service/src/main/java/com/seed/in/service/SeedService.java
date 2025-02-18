package com.seed.in.service;

import java.util.List;

import com.seed.in.dto.request.SeedDto;
import com.seed.in.entity.Seed;

public interface SeedService {

	Seed addSeedName(SeedDto seed);
	List<Seed> allSeeds();
	Seed addDescription(Seed description);
	void deleteSeedById(int seedId);
	Seed updateSeed(int id, SeedDto seed);
	Seed getSeedById(int id);
}
