package org.mklinkj.qna.spring_data_jpa_pageable.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.qna.spring_data_jpa_pageable.constant.Constants.PAGE_SIZE;
import static org.mklinkj.qna.spring_data_jpa_pageable.constant.Constants.TOTAL_EMPLOYEES;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mklinkj.qna.spring_data_jpa_pageable.config.RootConfig;
import org.mklinkj.qna.spring_data_jpa_pageable.repository.EmployeeRepository;
import org.mklinkj.qna.test.DatabaseInitExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith({DatabaseInitExtension.class})
@SpringJUnitConfig(classes = RootConfig.class)
@Slf4j
class EmployeeTests {
  @Autowired private EmployeeRepository repository;

  @Test
  void employeeCount() {
    List<Employee> result = repository.findAll();
    assertThat(result).isNotEmpty().hasSize(TOTAL_EMPLOYEES);
  }

  @Test
  void employeePageTest() {
    int pageNumber = 10; // 페이지 번호는 zero-based, 0이 1페이지
    int pageSize = PAGE_SIZE;

    PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

    Page<Employee> result = repository.findAll(pageRequest);

    // PageRequest의 요청의 페이비 번호는 0부터이지만,
    // getTotalPages()의 값은 페이지의 수 그대로이다.
    assertThat(result.getTotalPages()).isEqualTo(11);

    assertThat(result.getTotalElements()).isEqualTo(TOTAL_EMPLOYEES);

    assertThat(result.getSize()).isEqualTo(PAGE_SIZE);

    assertThat(result.hasPrevious()).isTrue();
    assertThat(result.hasNext()).isFalse();

    assertThat(result.isFirst()).isFalse();
    assertThat(result.isLast()).isTrue();

    assertThat(result.getContent()).hasSize(5);
  }
}
