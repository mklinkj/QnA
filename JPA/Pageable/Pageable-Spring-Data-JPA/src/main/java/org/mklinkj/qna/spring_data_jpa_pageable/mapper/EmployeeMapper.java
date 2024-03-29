package org.mklinkj.qna.spring_data_jpa_pageable.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.mklinkj.library.pagination.domain.PageRequest;
import org.mklinkj.qna.spring_data_jpa_pageable.vo.EmployeeVO;

@Mapper
public interface EmployeeMapper {
  List<EmployeeVO> findAll();

  List<EmployeeVO> findPaged(PageRequest pageRequest);

  long countAll();
}
