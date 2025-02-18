package com.in.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.entity.Plant;
import com.in.entity.PlantType;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long>{

   
    List<Plant> findByType(PlantType type);

}
