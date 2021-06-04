package com.dextra.harrypotter.exception;

public abstract class ResourceInvalidException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public ResourceInvalidException(String mensagem) {
		super(mensagem);
	}

}
