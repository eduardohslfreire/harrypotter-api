package com.dextra.harrypotter.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dextra.harrypotter.domain.assembler.CharacterInputDisassembler;
import com.dextra.harrypotter.domain.input.CharacterInput;
import com.dextra.harrypotter.domain.model.Character;
import com.dextra.harrypotter.openapi.CharacterOpenApi;
import com.dextra.harrypotter.service.CharacterService;
import com.dextra.harrypotter.service.HouseWithRetryService;

@RestController
@RequestMapping(path = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
public class CharacterController implements CharacterOpenApi {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CharacterService characterService;

	@Autowired
	HouseWithRetryService houseService;

	@Autowired
	CharacterInputDisassembler characterInputDisassembler;

	@GetMapping
	public List<Character> searchAll() {
		logger.debug("Listando todos os personagens.");
		return characterService.searchAll();
	}

	@GetMapping("/{characterId}")
	public Character search(Long characterId) {
		logger.debug("Buscando o personagem cujo o Id eh {} .", characterId);
		return characterService.searchOrFail(characterId);
	}

	@GetMapping(params = "house")
	public List<Character> searchAllByHouse(String house) {
		logger.debug("Buscando todos os personagens da casa cujo o Id eh {} .",
				house);
		return characterService.searchAllByHouse(house);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Character create(@RequestBody @Valid CharacterInput characterInput) {
		logger.debug("Realizando a criacao do personagem {}.",
				characterInput.getName());

		Character character = characterInputDisassembler.toDomainObject(characterInput);
		houseService.validHouse(character.getHouse());
		return characterService.save(character);
	}

	@PutMapping("/{characterId}")
	public Character update(@PathVariable Long characterId, @Valid @RequestBody CharacterInput characterInput) {
		logger.debug("Atualizando personagem de Id {} ", characterId);

		Character character = characterService.searchOrFail(characterId);
		characterInputDisassembler.copyToDomainObject(characterInput, character);
		return characterService.save(character);
	}

	@DeleteMapping("/{characterId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long characterId) {
		logger.debug("Excluindo personagem de Id {} ", characterId);

		characterService.delete(characterId);
	}
}
