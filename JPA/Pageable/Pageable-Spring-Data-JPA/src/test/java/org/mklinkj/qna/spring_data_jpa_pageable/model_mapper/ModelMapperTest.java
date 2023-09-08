package org.mklinkj.qna.spring_data_jpa_pageable.model_mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.qna.spring_data_jpa_pageable.config.ModelMapperConfig;
import org.mklinkj.qna.spring_data_jpa_pageable.entity.Employee;
import org.mklinkj.qna.spring_data_jpa_pageable.vo.EmployeeVO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = ModelMapperConfig.class)
class ModelMapperTest {

  @Autowired ModelMapper modelMapper;

  @Test
  void modelMapper() {
    Employee entity = new Employee();
    entity.setId(1);
    entity.setName("user01");

    EmployeeVO vo = modelMapper.map(entity, EmployeeVO.class);

    assertThat(vo) //
        .hasFieldOrPropertyWithValue("id", 1)
        .hasFieldOrPropertyWithValue("name", "user01");
  }
}
