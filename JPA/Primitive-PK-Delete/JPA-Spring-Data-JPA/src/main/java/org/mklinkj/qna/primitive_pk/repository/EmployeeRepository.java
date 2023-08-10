package org.mklinkj.qna.primitive_pk.repository;

import org.mklinkj.qna.primitive_pk.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {}
