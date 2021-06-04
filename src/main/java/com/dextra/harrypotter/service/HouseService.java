package com.dextra.harrypotter.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dextra.harrypotter.domain.dto.HousesDTO;
import com.dextra.harrypotter.domain.model.House;
import com.dextra.harrypotter.exception.HouseInvalidException;
import com.dextra.harrypotter.exception.SiteAccessException;

@Service
public class HouseService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${application.potter-api.houses.url}")
	private String potterApiHousesUrl;

	@Value("${application.potter-api.key}")
	private String apiKey;

	@CircuitBreaker(maxAttempts = 2, openTimeout = 30000l, resetTimeout = 10000l, include = RuntimeException.class, exclude = HouseInvalidException.class)
	public House retriveHouseById(String houseId) {

		logger.debug("Realizando a busca da casa {} na potterApi", houseId);
		long begin = System.currentTimeMillis();

		List<House> houses = retriveAllHouseFromPotterApi();
		House house = houses.stream().filter(h -> houseId.equals(h.getId())).findFirst()
				.orElseThrow(() -> new HouseInvalidException(houseId));

		logger.debug("Busca da casa {} na potterApi realizada em {} ms.",
				houseId, System.currentTimeMillis() - begin);

		return house;
	}

	public List<House> retriveAllHouseFromPotterApi() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("apikey", apiKey);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<HousesDTO> response = restTemplate.exchange(potterApiHousesUrl, HttpMethod.GET, entity,
				HousesDTO.class);

		if (!HttpStatus.OK.equals(response.getStatusCode())) {
			throw new SiteAccessException("Ocorreu um erro ao chamar potterApi");
		}

		return response.getBody().getHouses();
	}
}
