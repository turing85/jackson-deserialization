package de.turing85.jackson.deserialization;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Report {
  private final Status status;
  private final String inputStatus;

  @JsonCreator
  public Report(@JsonProperty("status") String inputStatus) {
    this.inputStatus = inputStatus;
    this.status = Status.getByNameOrDefault(inputStatus, Status.UNKNOWN);
  }
}
