package com.dextra.harrypotter.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.dextra.harrypotter.domain.model.Character;
import com.dextra.harrypotter.domain.model.House;
import com.dextra.harrypotter.exceptionhandler.ProblemType;
import com.dextra.harrypotter.repository.CharacterRepository;
import com.dextra.harrypotter.repository.HouseRepository;
import com.dextra.harrypotter.util.DatabaseCleaner;
import com.dextra.harrypotter.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CharacterControllerTest {

  private static final int    NON_EXISTENT_CHARACTER_ID = 1000;
  private static final String INVALID_PARAMETER         = "INVALID_PARAMETER";

  @LocalServerPort
  private int                 port;

  @Value("${server.servlet.context-path}")
  private String              contextPath;

  @Autowired
  private DatabaseCleaner     databaseCleaner;

  @Autowired
  private CharacterRepository characterRepository;

  @Autowired
  private HouseRepository     houseRepository;

  private int                 numberOfCharacters;
  private Character           characterRonWeasley;
  private String              jsonCorrectHarryPotter;
  private String              jsonCorrectRonWeasley;
  private String              jsonCorrectSeverusSnape;
  private String              jsonIncorrectDracoMalfoy;
  private String              jsonIncorrectFiliusFlitwick;
  private String              jsonIncorrectCedricDiggory;

  @Before
  public void setUp() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    RestAssured.port = port;
    RestAssured.basePath = contextPath + "/characters";

    jsonCorrectHarryPotter = ResourceUtils.getContentFromResource("/json/correct/gryffindor-character-harry-potter.json");
    jsonCorrectRonWeasley = ResourceUtils.getContentFromResource("/json/correct/gryffindor-character-ron-weasley.json");
    jsonCorrectSeverusSnape = ResourceUtils.getContentFromResource("/json/correct/slytherin-character-severus-snape.json");
    jsonIncorrectDracoMalfoy = ResourceUtils.getContentFromResource("/json/incorrect/slytherin-character-draco-malfoy.json");
    jsonIncorrectFiliusFlitwick = ResourceUtils.getContentFromResource("/json/incorrect/ravenclaw-character-filius-flitwick.json");
    jsonIncorrectCedricDiggory = ResourceUtils.getContentFromResource("/json/incorrect/hufflepuff-character-cedric-diggory.json");

    databaseCleaner.clearTables();
    prepareData();
  }

  @Test
  public void shouldReturnStatus200_WhenSearchCharacter() {
    given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
  }

  @Test
  public void shouldReturnNumberOfCharacters_WhenSearchCharacter() {
    given().accept(ContentType.JSON).when().get().then().body("", hasSize(numberOfCharacters));
  }

  @Test
  public void shouldReturnCorrectDataAndStatus_WhenSearchExistentCharacter() {
    given().pathParam("characterId", characterRonWeasley.getId())
           .accept(ContentType.JSON)
           .when()
           .get("/{characterId}")
           .then()
           .statusCode(HttpStatus.OK.value())
           .body("name", equalTo(characterRonWeasley.getName()));
  }

  @Test
  public void shouldReturnStatus400_WhenSearchCharacterWithInvalidParameter() {
    given().pathParam("characterId", INVALID_PARAMETER)
           .accept(ContentType.JSON)
           .when()
           .get("/{characterId}")
           .then()
           .statusCode(HttpStatus.BAD_REQUEST.value())
           .body("title", equalTo(ProblemType.INVALID_PARAMETER.getTitle()));
  }

  @Test
  public void shouldReturnStatus404_WhenSearchNonExistentCharacter() {
    given().pathParam("characterId", NON_EXISTENT_CHARACTER_ID)
           .accept(ContentType.JSON)
           .when()
           .get("/{characterId}")
           .then()
           .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void shouldReturnStatus201_WhenCreateCharacter() {
    given().body(jsonCorrectHarryPotter)
           .contentType(ContentType.JSON)
           .accept(ContentType.JSON)
           .when()
           .post()
           .then()
           .statusCode(HttpStatus.CREATED.value());
  }

  @Test
  public void shouldReturnStatus400_WhenCreateCharacterWithoutPatronus() {
    given().body(jsonIncorrectDracoMalfoy)
           .contentType(ContentType.JSON)
           .accept(ContentType.JSON)
           .when()
           .post()
           .then()
           .statusCode(HttpStatus.BAD_REQUEST.value())
           .body("title", equalTo(ProblemType.INVALID_DATA.getTitle()));
  }

  @Test
  public void shouldReturnStatus400_WhenCreateCharacterWithInvalidHouse() {
    given().body(jsonIncorrectCedricDiggory)
           .contentType(ContentType.JSON)
           .accept(ContentType.JSON)
           .when()
           .post()
           .then()
           .statusCode(HttpStatus.BAD_REQUEST.value())
           .body("title", equalTo(ProblemType.INVALID_DATA.getTitle()));
  }

  @Test
  public void shouldReturnStatus400_WhenCreateCharacterWithInvalidProperty() {
    given().body(jsonIncorrectFiliusFlitwick)
           .contentType(ContentType.JSON)
           .accept(ContentType.JSON)
           .when()
           .post()
           .then()
           .statusCode(HttpStatus.BAD_REQUEST.value())
           .body("title", equalTo(ProblemType.INCOMPREHENSIBLE_MESSAGE.getTitle()));
  }

  @Test
  public void shouldReturnStatus404_WhenUpdateCharacterWithInvalidParameter() {
    given().body(jsonCorrectSeverusSnape)
           .contentType(ContentType.JSON)
           .pathParam("characterId", INVALID_PARAMETER)
           .accept(ContentType.JSON)
           .when()
           .put("/{characterId}")
           .then()
           .statusCode(HttpStatus.BAD_REQUEST.value())
           .body("title", equalTo(ProblemType.INVALID_PARAMETER.getTitle()));
  }

  @Test
  public void shouldReturnCorrectDataAndStatus_WhenUpdateExistentCharacter() {
    given().body(jsonCorrectRonWeasley)
           .contentType(ContentType.JSON)
           .pathParam("characterId", characterRonWeasley.getId())
           .accept(ContentType.JSON)
           .when()
           .get("/{characterId}")
           .then()
           .statusCode(HttpStatus.OK.value())
           .body("name", equalTo(characterRonWeasley.getName()));
  }

  @Test
  public void shouldReturnStatus404_WhenDeleteNonExistentCharacter() {
    given().pathParam("characterId", NON_EXISTENT_CHARACTER_ID)
           .accept(ContentType.JSON)
           .when()
           .delete("/{characterId}")
           .then()
           .statusCode(HttpStatus.NOT_FOUND.value());
  }

  @Test
  public void shouldReturnCorrectStatus_WhenDeleteExistentCharacter() {
    given().body(jsonCorrectRonWeasley)
           .contentType(ContentType.JSON)
           .pathParam("characterId", characterRonWeasley.getId())
           .accept(ContentType.JSON)
           .when()
           .delete("/{characterId}")
           .then()
           .statusCode(HttpStatus.NO_CONTENT.value());
  }

  private void prepareData() {
    characterRonWeasley = new Character();
    characterRonWeasley.setName("Ron Weasley");
    characterRonWeasley.setRole("student");
    characterRonWeasley.setSchool("Hogwarts School of Witchcraft and Wizardry");
    characterRonWeasley.setPatronus("Jack Russell Terrier");
    characterRonWeasley.setHouse("1760529f-6d51-4cb1-bcb1-25087fce5bde");

    characterRepository.save(characterRonWeasley);
    numberOfCharacters = (int) characterRepository.count();

    House houseGryffindor = new House();
    houseGryffindor.setId("1760529f-6d51-4cb1-bcb1-25087fce5bde");
    houseGryffindor.setName("Gryffindor");

    houseRepository.save(houseGryffindor);
  }

  @After
  public void destroy() {
    databaseCleaner.clearTables();
  }
}
