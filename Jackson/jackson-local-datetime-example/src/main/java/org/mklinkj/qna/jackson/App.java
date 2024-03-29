package org.mklinkj.qna.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {

  public static void main(String[] args) throws Exception {
    Member member = new Member();
    member.setName("user00");
    member.setRegisterDate(LocalDateTime.of(2023, 12, 25, 1, 2, 3));

    System.out.printf("1. legacySerialize(member): %s%n", legacySerialize(member));
    System.out.printf("2. serialize(member): %s%n", serialize(member));
  }

  public static String legacySerialize(Member member) throws Exception {
    // It’s a non-recommended method of specifying the date format,
    // and this method does not apply the date format in version 2.16.0.
    ObjectMapper mapper =
        new ObjectMapper()
            .registerModule(
                new JavaTimeModule()
                    .addSerializer(
                        LocalDateTime.class,
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("MM/dd/yyyy"))));
    return mapper.writeValueAsString(member);
  }

  public static String serialize(Member member) throws Exception {
    ObjectMapper mapper =
        new ObjectMapper() //
            .registerModule(new JavaTimeModule());

    mapper
        .configOverride(LocalDateTime.class) //
        .setFormat(JsonFormat.Value.forPattern("MM/dd/yyyy"));
    return mapper.writeValueAsString(member);
  }
}
