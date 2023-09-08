package org.mklinkj.qna.spring_data_jpa_pageable.repository;

import org.mklinkj.qna.spring_data_jpa_pageable.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
