package org.mklinkj.qna.spring_guide.relational_data_access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
  private long id;
  private String firstName;
  private String lastName;

  @Override
  public String toString() {
    return String.format("Customer[id=%d, firstName='%s', lastName='%s']", id, firstName, lastName);
  }
}
