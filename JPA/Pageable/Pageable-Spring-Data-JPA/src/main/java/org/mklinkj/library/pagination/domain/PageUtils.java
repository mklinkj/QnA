package org.mklinkj.library.pagination.domain;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.StringJoiner;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageUtils {

  public static String getPageParameters(
      PageRequest pageRequest, LinkedHashMap<String, String> searchParams) {
    StringJoiner joiner = new StringJoiner("&");
    joiner.add("page=" + pageRequest.getPage());
    joiner.add("size=" + pageRequest.getSize());

    searchParams.entrySet().stream()
        .forEach(
            entry ->
                joiner.add(
                    String.format(
                        "%s=%s",
                        entry.getKey(),
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))));

    return joiner.toString();
  }
}
