package de.turing85.jackson.deserialization;

import java.util.Locale;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ReportTest {
  private static final String JSON_TEMPLATE = """
      {
        "status": "%s"
      }""";

  @ParameterizedTest
  @EnumSource(Status.class)
  void deserializeStatusWithKnownStatus(Status status) throws JsonProcessingException {
    // given
    final String expectedInputStatus = status.name().toLowerCase(Locale.ROOT);
    final String json = JSON_TEMPLATE.formatted(expectedInputStatus);
    final ObjectMapper mapper = new ObjectMapper();

    // when
    final Report report = mapper.readValue(json, Report.class);

    // then
    Truth.assertThat(report.getStatus()).isSameInstanceAs(status);
    Truth.assertThat(report.getInputStatus()).isEqualTo(expectedInputStatus);
  }

  @Test
  void deserializeStatusWithUnknownStatus() throws JsonProcessingException {
    // given
    final String expectedInputStatus = "foobar";
    final String json = JSON_TEMPLATE.formatted(expectedInputStatus);
    final ObjectMapper mapper = new ObjectMapper();

    // when
    final Report report = mapper.readValue(json, Report.class);

    // then
    Truth.assertThat(report.getStatus()).isSameInstanceAs(Status.UNKNOWN);
    Truth.assertThat(report.getInputStatus()).isEqualTo(expectedInputStatus);
  }
}
