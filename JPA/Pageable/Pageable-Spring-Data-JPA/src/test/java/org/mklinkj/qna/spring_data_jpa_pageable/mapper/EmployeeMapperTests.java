package org.mklinkj.qna.spring_data_jpa_pageable.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.qna.spring_data_jpa_pageable.constant.Constants.TOTAL_EMPLOYEES;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mklinkj.qna.spring_data_jpa_pageable.config.RootConfig;
import org.mklinkj.qna.spring_data_jpa_pageable.vo.EmployeeVO;
import org.mklinkj.qna.test.DatabaseInitExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith({DatabaseInitExtension.class})
@SpringJUnitConfig(classes = RootConfig.class)
class EmployeeMapperTests {
  @Autowired EmployeeMapper employeeMapper;

  @Test
  void findAll() {
    List<EmployeeVO> result = employeeMapper.findAll();
    assertThat(result).isNotEmpty().hasSize(TOTAL_EMPLOYEES);
  }

  @Test
  void countAll() {
    int count = employeeMapper.countAll();
    assertThat(count).isEqualTo(TOTAL_EMPLOYEES);
  }
}
