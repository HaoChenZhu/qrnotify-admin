package com.nebrija.tfg.qrnotify.admin.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.util.Objects;

/**
 * ApiTopicResponseDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-05-01T21:40:09.278631300+02:00[Europe/Paris]")
public class ApiTopicResponseDto {
  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("publish_name")
  private String publishName;

  @JsonProperty("destinations")
  private ApiDestination destinations;

  @JsonProperty("created_date")
  private String createdDate;

  @JsonProperty("modificatied_date")
  private String modificatiedDate;

  @JsonProperty("deleted_date")
  private String deletedDate;

  public ApiTopicResponseDto id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @ApiModelProperty(example = "13214", value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ApiTopicResponseDto name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @ApiModelProperty(example = "Fruteria", value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ApiTopicResponseDto description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  */
  @ApiModelProperty(example = "Fruteria de XXX", value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ApiTopicResponseDto publishName(String publishName) {
    this.publishName = publishName;
    return this;
  }

  /**
   * Get publishName
   * @return publishName
  */
  @ApiModelProperty(example = "MERCADO/XXX/FRUTERIA", value = "")


  public String getPublishName() {
    return publishName;
  }

  public void setPublishName(String publishName) {
    this.publishName = publishName;
  }

  public ApiTopicResponseDto destinations(ApiDestination destinations) {
    this.destinations = destinations;
    return this;
  }

  /**
   * Get destinations
   * @return destinations
  */
  @ApiModelProperty(value = "")

  @Valid

  public ApiDestination getDestinations() {
    return destinations;
  }

  public void setDestinations(ApiDestination destinations) {
    this.destinations = destinations;
  }

  public ApiTopicResponseDto createdDate(String createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  /**
   * Get createdDate
   * @return createdDate
  */
  @ApiModelProperty(value = "")


  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public ApiTopicResponseDto modificatiedDate(String modificatiedDate) {
    this.modificatiedDate = modificatiedDate;
    return this;
  }

  /**
   * Get modificatiedDate
   * @return modificatiedDate
  */
  @ApiModelProperty(value = "")


  public String getModificatiedDate() {
    return modificatiedDate;
  }

  public void setModificatiedDate(String modificatiedDate) {
    this.modificatiedDate = modificatiedDate;
  }

  public ApiTopicResponseDto deletedDate(String deletedDate) {
    this.deletedDate = deletedDate;
    return this;
  }

  /**
   * Get deletedDate
   * @return deletedDate
  */
  @ApiModelProperty(value = "")


  public String getDeletedDate() {
    return deletedDate;
  }

  public void setDeletedDate(String deletedDate) {
    this.deletedDate = deletedDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApiTopicResponseDto topicResponseDto = (ApiTopicResponseDto) o;
    return Objects.equals(this.id, topicResponseDto.id) &&
        Objects.equals(this.name, topicResponseDto.name) &&
        Objects.equals(this.description, topicResponseDto.description) &&
        Objects.equals(this.publishName, topicResponseDto.publishName) &&
        Objects.equals(this.destinations, topicResponseDto.destinations) &&
        Objects.equals(this.createdDate, topicResponseDto.createdDate) &&
        Objects.equals(this.modificatiedDate, topicResponseDto.modificatiedDate) &&
        Objects.equals(this.deletedDate, topicResponseDto.deletedDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, publishName, destinations, createdDate, modificatiedDate, deletedDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApiTopicResponseDto {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    publishName: ").append(toIndentedString(publishName)).append("\n");
    sb.append("    destinations: ").append(toIndentedString(destinations)).append("\n");
    sb.append("    createdDate: ").append(toIndentedString(createdDate)).append("\n");
    sb.append("    modificatiedDate: ").append(toIndentedString(modificatiedDate)).append("\n");
    sb.append("    deletedDate: ").append(toIndentedString(deletedDate)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

