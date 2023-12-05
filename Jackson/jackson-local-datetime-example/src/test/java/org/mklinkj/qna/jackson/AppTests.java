package org.mklinkj.qna.jackson;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AppTests {
  @Test
  void testSerialize() throws Exception {

    Member member = new Member();
    member.setName("user00");
    member.setRegisterDate(LocalDateTime.of(2023, 12, 25, 1, 2, 3));

    String legacySerializeResult = App.legacySerialize(member);
    String serializeResult = App.serialize(member);

    assertThatJson(App.serialize(member))
        .and(
            a -> a.node("name").isEqualTo("user00"), //
            a -> a.node("registerDate").isEqualTo("12/25/2023"));

    assertThatJson(App.legacySerialize(member))
        .and(
            a -> a.node("name").isEqualTo("user00"), //
            a -> a.node("registerDate").isEqualTo("12/25/2023"));

    assertThatJson(legacySerializeResult).isEqualTo(serializeResult);
  }
}
