package com.dextra.harrypotter.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dextra.harrypotter.exceptionhandler.Problem;
import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

	@Bean
	public Docket apiDocket() {
		TypeResolver typeResolver = new TypeResolver();

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.dextra.harrypotter")).paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false).globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
				.globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
				.additionalModels(typeResolver.resolve(Problem.class)).apiInfo(apiInfo())
				.tags(new Tag("Characters", "Api para CRUD dos personagens do filme Harry Potter"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Harry Potter API - Dextra")
				.description("API do Harry Potter cadastro dos personagens").version("1.0")
				.license("Licença - Open Source").contact(this.contato()).build();
	}

	private Contact contato() {
		return new Contact("Dextra", "https://www.dextra.com.br", "eduardo.freire@dextra.com");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	private List<ResponseMessage> globalGetResponseMessages() {
		return Arrays.asList(
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno do servidor").responseModel(new ModelRef("Problem")).build(),
				new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value()).message("Personagem nao encontrado")
						.responseModel(new ModelRef("Problem")).build());
	}

	private List<ResponseMessage> globalPostPutResponseMessages() {
		return Arrays.asList(new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
				.message("Requisição inválida (erro do cliente)").responseModel(new ModelRef("Problem")).build(),
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
						.message("Erro interno no servidor").responseModel(new ModelRef("Problem")).build(),
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
						.message("Requisição recusada porque o corpo está em um formato não suportado")
						.responseModel(new ModelRef("Problem")).build());
	}
}
