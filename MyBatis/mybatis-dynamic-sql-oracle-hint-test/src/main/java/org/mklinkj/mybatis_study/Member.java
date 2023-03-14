package org.mklinkj.mybatis_study;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
  private String id;
  private String pwd;
  private String name;
  private LocalDate joinDate;
}
