package com.dextra.harrypotter.openapi;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.dextra.harrypotter.domain.input.CharacterInput;
import com.dextra.harrypotter.domain.model.Character;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Characters")
public interface CharacterOpenApi {

	@ApiOperation(value = "Lista todos os personagens")
	public List<Character> searchAll();

	@ApiOperation(value = "Busca um personagem pelo ID")
	public Character search(
			@PathVariable @ApiParam(value = "ID de um personagem", example = "1", required = true) Long characterId);

	@ApiOperation(value = "Cria um personagem")
	public Character create(@RequestBody CharacterInput characterInput);

	@ApiOperation(value = "Atualiza um personagem")
	public Character update(
			@PathVariable @ApiParam(value = "ID de um personagem", example = "1", required = true) Long characterId,
			@RequestBody CharacterInput characterInput);

	@ApiOperation(value = "Deleta um personagem")
	public void delete(
			@PathVariable @ApiParam(value = "ID de um personagem", example = "1", required = true) Long characterId);

}
