package org.mklinkj.qna.spring_data_jpa_pageable.vo;

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

  public EmployeeVO(String name) {
    this.name = name;
  }

  private Integer id;

  private String name;
}
