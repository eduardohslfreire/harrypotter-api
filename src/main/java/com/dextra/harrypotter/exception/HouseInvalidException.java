package com.dextra.harrypotter.exception;

public class HouseInvalidException extends ResourceInvalidException {

	private static final long serialVersionUID = 1L;

	public HouseInvalidException(String houseId) {
		super(String.format("Nao existe casa valida para o Id %s", houseId));
	}

}
