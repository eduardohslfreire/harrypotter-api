package com.dextra.harrypotter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dextra.harrypotter.domain.model.Character;
import com.dextra.harrypotter.exception.CharacterNotFoundException;
import com.dextra.harrypotter.repository.CharacterRepository;

@Service
public class CharacterService {

	@Autowired
	private CharacterRepository characterRepository;

	public List<Character> searchAll() {
		return characterRepository.findAll();
	}

	public Character searchOrFail(Long characterId) {
		return characterRepository.findById(characterId).orElseThrow(() -> new CharacterNotFoundException(characterId));
	}

	public List<Character> searchAllByHouse(String houseId) {
		return characterRepository.findAllByHouse(houseId);
	}

	@Transactional
	public Character save(Character character) {
		return characterRepository.save(character);
	}

	@Transactional
	public void delete(Long characterId) {
		try {
			characterRepository.deleteById(characterId);
			characterRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CharacterNotFoundException(characterId);
		}
	}

}
