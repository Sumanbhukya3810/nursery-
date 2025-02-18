package com.planter.in.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planter.in.entity.Planter;

public interface PlanterRepository extends JpaRepository<Planter, Integer> {

	
}
