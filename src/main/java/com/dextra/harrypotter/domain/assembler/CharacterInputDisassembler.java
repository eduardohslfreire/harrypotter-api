package com.dextra.harrypotter.domain.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dextra.harrypotter.domain.input.CharacterInput;
import com.dextra.harrypotter.domain.model.Character;

@Component
public class CharacterInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;

	public Character toDomainObject(CharacterInput characterInput) {
		return modelMapper.map(characterInput, Character.class);
	}

	public void copyToDomainObject(CharacterInput characterInput, Character character) {
		modelMapper.map(characterInput, character);
	}
}
