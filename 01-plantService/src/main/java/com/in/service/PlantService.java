package com.in.service;

import java.util.List;

import com.in.entity.Plant;
import com.in.entity.PlantType;
import com.in.exception.InvalidPlantException;

public interface PlantService {
public List<Plant> getAllPlants()throws InvalidPlantException;
public Plant getPlantById(long id) throws InvalidPlantException;
public Plant savePlant(Plant p)throws InvalidPlantException;
public Plant updatePlant(Plant p, long id)throws InvalidPlantException;
public Plant deletePlant(long id)throws InvalidPlantException;
public List<Plant> searchByType(PlantType type)throws InvalidPlantException;

}
   