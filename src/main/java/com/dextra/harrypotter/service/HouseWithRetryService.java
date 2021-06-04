package com.dextra.harrypotter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.dextra.harrypotter.domain.model.House;
import com.dextra.harrypotter.exception.HouseInvalidException;
import com.dextra.harrypotter.repository.HouseRepository;

@Service
public class HouseWithRetryService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private HouseService houseService;

	@Autowired
	private HouseRepository houseCacheRepository;

	@Retryable(maxAttempts = 3, include = RuntimeException.class, exclude = HouseInvalidException.class)
	public House validHouse(String houseId) {
		logger.debug("Realizando validacao da casa {}", houseId);
		House house = houseService.retriveHouseById(houseId);
		houseCacheRepository.save(house);
		return house;
	}

	@Recover
	public House retriveCacheHouseById(String houseId) {
		logger.debug(
				"Nao foi possivel realizar consulta da casa {} pela potterApi, sera realizado a consulta no cache.",
				houseId);
		
		return houseCacheRepository.findById(houseId).orElseThrow(() -> new HouseInvalidException(houseId));
	}

}
