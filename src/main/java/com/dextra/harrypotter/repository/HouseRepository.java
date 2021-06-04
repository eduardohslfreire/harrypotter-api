package com.dextra.harrypotter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dextra.harrypotter.domain.model.House;

@Repository
public interface HouseRepository extends CrudRepository<House, String> {
}
