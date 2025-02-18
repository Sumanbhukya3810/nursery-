package com.in.service;

import java.util.List;

import com.in.entity.Fertilizer;
import com.in.entity.FertilizerType;
import com.in.exception.InvalidFertilizerException;

public interface FertilizerService {

public List<Fertilizer> getAllFertilizers()throws InvalidFertilizerException;
public Fertilizer getFertilizerById(long id) throws InvalidFertilizerException;
public Fertilizer saveFertilizer(Fertilizer p)throws InvalidFertilizerException;
public Fertilizer updateFertilizer(Fertilizer p, long id)throws InvalidFertilizerException;
public Fertilizer deleteFertilizer(long id)throws InvalidFertilizerException;
public List<Fertilizer> searchByType(FertilizerType type)throws InvalidFertilizerException;
}
