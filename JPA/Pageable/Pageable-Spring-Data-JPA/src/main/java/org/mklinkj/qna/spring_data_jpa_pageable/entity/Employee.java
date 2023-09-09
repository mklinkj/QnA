package org.mklinkj.qna.spring_data_jpa_pageable.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "t_employee")
public class Employee {

  public Employee(String name, String location, LocalDate registerDate) {
    this.name = name;
    this.location = location;
    this.registerDate = registerDate;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private String location;

  @Column(name = "register_date")
  private LocalDate registerDate;
}
