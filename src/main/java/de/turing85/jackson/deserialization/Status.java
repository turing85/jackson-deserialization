package de.turing85.jackson.deserialization;

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Status {
  // @formatter:off
  SUCCESS,
  FAILURE,
  @JsonEnumDefaultValue
  UNKNOWN;
  // @formatter:on

  public static Optional<Status> getByName(String name) {
    return Arrays.stream(Status.values())
        .filter(
            status -> status.name().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
        .findFirst();
  }

  public static Status getByNameOrDefault(String name, Status status) {
    return getByName(name).orElse(status);
  }
}
