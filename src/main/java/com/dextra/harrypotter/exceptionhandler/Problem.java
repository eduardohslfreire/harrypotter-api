package com.dextra.harrypotter.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Problem")
public class Problem {

  @ApiModelProperty(example = "400", position = 1)
  private Integer        status;

  @ApiModelProperty(example = "2021-06-02T18:09:02.70844Z", position = 5)
  private OffsetDateTime timestamp;

  @ApiModelProperty(example = "Dados inválidos", position = 10)
  private String         title;

  @ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 25)
  private String         message;

  @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", position = 30)
  private List<Detail>   details;

  public static Problem builder() {
    return new Problem();
  }

  public Problem status(Integer status) {
    this.status = status;
    return this;
  }

  public Problem timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public Problem title(String title) {
    this.title = title;
    return this;
  }

  public Problem message(String message) {
    this.message = message;
    return this;
  }

  public Problem details(List<Detail> details) {
    this.details = details;
    return this;
  }

  public Integer getStatus() {
    return status;
  }

  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public String getTitle() {
    return title;
  }

  public String getMessage() {
    return message;
  }

  public List<Detail> getDetails() {
    return details;
  }

  @ApiModel("DetailProblem")
  public static class Detail {

    @ApiModelProperty(example = "name")
    private String name;

    @ApiModelProperty(example = "A propriedade 'name' não pode ser nula ou vazia")
    private String message;

    public static Detail builder() {
      return new Detail();
    }

    public String getName() {
      return name;
    }

    public String getMessage() {
      return message;
    }

    public Detail name(String name) {
      this.name = name;
      return this;
    }

    public Detail message(String message) {
      this.message = message;
      return this;
    }
  }
}
