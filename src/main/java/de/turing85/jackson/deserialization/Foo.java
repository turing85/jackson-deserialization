package de.turing85.jackson.deserialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Foo {
  private Status status;
  private String statusText;

  @JsonProperty("status")
  public void setStatusText(String statusText) {
    this.statusText = statusText;
    this.setStatus(Status.getByNameOrDefault(statusText, Status.UNKNOWN));
  }

  @JsonIgnore
  private void setStatus(Status status) {
    this.status = status;
  }


}
