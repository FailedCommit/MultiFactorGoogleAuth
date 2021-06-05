package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ReadByte
 */
@Entity
@Data
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-03-14T05:07:25.653Z[GMT]")
@Table(name="\"read_byte\"")

public class ReadByte   {
  @JsonProperty("id")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id = null;

  @JsonProperty("tag")
  private String tag = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  public ReadByte id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
     // @NotNull

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ReadByte tag(String tag) {
    this.tag = tag;
    return this;
  }

  /**
   * tag to determine the type of the blog i.e. sports,news,tech,art etc.
   * @return tag
   **/
  @Schema(description = "tag to determine the type of the blog i.e. sports,news,tech,art etc.")
  
    public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public ReadByte url(String url) {
    this.url = url;
    return this;
  }

  /**
   * URL of the original link
   * @return url
   **/
  @Schema(description = "URL of the original link")
  
    public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public ReadByte title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Title of the original article
   * @return title
   **/
  @Schema(description = "Title of the original article")
  
    public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public ReadByte description(String description) {
    this.description = description;
    return this;
  }

  /**
   * brief description of the content in the URL
   * @return description
   **/
  @Schema(description = "brief description of the content in the URL")
  
    public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReadByte readByte = (ReadByte) o;
    return Objects.equals(this.id, readByte.id) &&
        Objects.equals(this.tag, readByte.tag) &&
        Objects.equals(this.url, readByte.url) &&
        Objects.equals(this.title, readByte.title) &&
        Objects.equals(this.description, readByte.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, tag, url, title, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReadByte {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
