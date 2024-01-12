package de.turing85.jackson.deserialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Report {
  private Status status;
  private String inputStatus;

  @JsonProperty("status")
  public void setInputStatus(String inputStatus) {
    this.inputStatus = inputStatus;
    this.setStatus(Status.getByNameOrDefault(inputStatus, Status.UNKNOWN));
  }

  @JsonIgnore
  private void setStatus(Status status) {
    this.status = status;
  }
}
