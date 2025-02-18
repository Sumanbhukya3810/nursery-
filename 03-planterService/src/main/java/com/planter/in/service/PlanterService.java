package com.planter.in.service;

import java.util.List;

import com.planter.in.dto.request.PlanterDto;
import com.planter.in.entity.Planter;

public interface PlanterService {
	
	Planter addPlanter(PlanterDto planter);
	List<Planter> allPlanters();
	Planter addDescription(Planter description);
	void deletePlanterById(int planterId);
	Planter updatePlanter(int id, PlanterDto planter);
	Planter getPlanterByPrice(float price);

}
