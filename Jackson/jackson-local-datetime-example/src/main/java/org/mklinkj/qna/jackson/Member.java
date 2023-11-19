package org.mklinkj.qna.jackson;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

  private String name;

  private LocalDateTime registerDate;
}
