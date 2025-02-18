package com.planter.in.service;

import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planter.in.dto.request.PlanterDto;
import com.planter.in.entity.Planter;
import com.planter.in.exception.ApplicationException;
import com.planter.in.repository.PlanterRepository;

@Service
public class PlanterServiceImpl implements PlanterService {
	
	@Autowired
	private PlanterRepository plantRepo;

	@Override
	public Planter addPlanter(PlanterDto planter) {
		// TODO Auto-generated method stub
		Planter p = new Planter();
		BeanUtils.copyProperties(planter, p);
		return plantRepo.save(p);
	}

	@Override
	public List<Planter> allPlanters() {
		// TODO Auto-generated method stub
		return plantRepo.findAll();
	}

	@Override
	public Planter addDescription(Planter description) {
		// TODO Auto-generated method stub
		return plantRepo.save(description);
	}

	@Override
	public void deletePlanterById(int planterId) {
		// TODO Auto-generated method stub
		plantRepo.deleteById(planterId);
		
	}

	@Override
	public Planter updatePlanter(int id, PlanterDto planter) {
		// TODO Auto-generated method stub
		plantRepo.findById(id).orElseThrow(()-> new ApplicationException("Planter not found"));
		Planter p = new Planter();
		BeanUtils.copyProperties(planter, p);
		return plantRepo.save(p);
	}

//	@Override
//	public Planter getPlanterById(int id) {
//		// TODO Auto-generated method stub
//		Optional<Planter> byId = plantRepo.findById(id);
//		if (byId.isPresent()) {
//			return byId.get();
//		}
//		return null;
//	}

	@Override
	public Planter getPlanterByPrice(float price) {
		// TODO Auto-generated method stub
		List<Planter> planterList = allPlanters();
	    for (Planter planter : planterList) {
	        if (planter.getPrice() == price) {
	            return planter;
	        }
	    }
		return null;
	}
	
}
