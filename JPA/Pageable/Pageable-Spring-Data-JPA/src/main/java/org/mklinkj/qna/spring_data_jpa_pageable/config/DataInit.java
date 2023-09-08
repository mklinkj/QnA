package org.mklinkj.qna.spring_data_jpa_pageable.config;

import static org.mklinkj.qna.spring_data_jpa_pageable.constant.Constants.TOTAL_EMPLOYEES;

import jakarta.annotation.PostConstruct;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.qna.spring_data_jpa_pageable.domain.Employee;
import org.mklinkj.qna.spring_data_jpa_pageable.repository.EmployeeRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInit {

  private final EmployeeRepository employeeRepository;

  @Transactional
  @PostConstruct
  public void initData() {
    IntStream.rangeClosed(1, TOTAL_EMPLOYEES)
        .forEach(i -> employeeRepository.save(new Employee("empId_%s".formatted(i))));
  }
}
