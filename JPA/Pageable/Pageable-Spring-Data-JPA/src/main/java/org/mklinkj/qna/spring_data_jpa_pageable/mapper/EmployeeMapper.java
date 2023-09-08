package org.mklinkj.qna.spring_data_jpa_pageable.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.mklinkj.qna.spring_data_jpa_pageable.domain.Employee;

@Mapper
public interface EmployeeMapper {
  List<Employee> findAll();

  int countAll();
}
