package com.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.entity.Fertilizer;
import com.in.entity.FertilizerType;

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long>{
	public List<Fertilizer>findByType(FertilizerType type);
}
