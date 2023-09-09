package org.mklinkj.qna.spring_data_jpa_pageable.vo;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("employeeVO")
@NoArgsConstructor
public class EmployeeVO {

  public EmployeeVO(String name, String location, LocalDate registerDate) {
    this.name = name;
    this.location = location;
    this.registerDate = registerDate;
  }

  private Integer id;

  private String name;

  private String location;

  private LocalDate registerDate;
}
