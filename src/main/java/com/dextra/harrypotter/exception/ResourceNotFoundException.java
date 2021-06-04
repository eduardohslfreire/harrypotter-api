package com.dextra.harrypotter.exception;

public abstract class ResourceNotFoundException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}

}
