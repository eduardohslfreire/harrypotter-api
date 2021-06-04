package com.dextra.harrypotter.exception;

public class CharacterNotFoundException extends ResourceNotFoundException {

	private static final long serialVersionUID = 1L;

	public CharacterNotFoundException(String mensagem) {
		super(mensagem);
	}

	public CharacterNotFoundException(Long characterId) {
		this(String.format("Não existe personagem com o Id %d", characterId));
	}

}
