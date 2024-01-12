package de.turing85.jackson.deserialization;

import java.util.Locale;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.Truth;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class FooTest {
  @ParameterizedTest
  @EnumSource(Status.class)
  void deserializeStatusWithKnownStatus(Status status) throws JsonProcessingException {
    // given
    final String expectedStatusText = status.name().toUpperCase(Locale.ROOT);
    final String fooJson = """
        {
          "status": "%s"
        }""".formatted(expectedStatusText);
    final ObjectMapper mapper = new ObjectMapper();

    // when
    final Foo foo = mapper.readValue(fooJson, Foo.class);

    // then
    Truth.assertThat(foo.getStatus()).isSameInstanceAs(status);
    Truth.assertThat(foo.getStatusText()).isEqualTo(expectedStatusText);
  }

  @Test
  void deserializeStatusWithUnknownStatus() throws JsonProcessingException {
    // given
    final String expectedStatusText = "foobar";
    final String fooJson = """
        {
          "status": "%s"
        }""".formatted(expectedStatusText);
    final ObjectMapper mapper = new ObjectMapper();

    // when
    final Foo foo = mapper.readValue(fooJson, Foo.class);

    // then
    Truth.assertThat(foo.getStatus()).isSameInstanceAs(Status.UNKNOWN);
    Truth.assertThat(foo.getStatusText()).isEqualTo(expectedStatusText);
  }
}
