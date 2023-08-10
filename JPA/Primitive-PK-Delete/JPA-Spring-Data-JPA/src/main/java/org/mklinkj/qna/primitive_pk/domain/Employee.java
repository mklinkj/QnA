package org.mklinkj.qna.primitive_pk.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_employee")
public class Employee implements Persistable<Integer> {
  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @Override
  public Integer getId() {
    return id;
  }

  @Override
  public boolean isNew() {
    return id == -1;
  }
}
