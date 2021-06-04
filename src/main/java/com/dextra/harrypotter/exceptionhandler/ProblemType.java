package com.dextra.harrypotter.exceptionhandler;

public enum ProblemType {
	RESOURCE_NOT_FOUND("/recurso-nao-encontrado", "Recurso não encontrado"),
	INVALID_DATA("/dados-invalidos", "Dados inválidos"), SYSTEM_ERROR("/erro-de-sistema", "Erro de sistema"),
	INCOMPREHENSIBLE_MESSAGE("/mensagem-incompreensivel", "Mensagem incompreensível"),
	INVALID_PARAMETER("/parametro-invalido", "Parâmetro inválido"),
	BUSINESS_ERROR("/erro-negocio", "Violação de regra de negócio");

	private String title;
	private String uri;

	ProblemType(String path, String title) {
		this.uri = "http://dextra.com" + path;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
