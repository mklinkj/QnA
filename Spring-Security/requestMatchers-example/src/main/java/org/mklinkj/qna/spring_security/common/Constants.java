package org.mklinkj.qna.spring_security.common;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
  public static final Charset PROJECT_ENCODING = StandardCharsets.UTF_8;

  public static final String PROJECT_ENCODING_VALUE = PROJECT_ENCODING.name();
}
