package org.mklinkj.qna.hibernate.join_column_test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Student {
  @EqualsAndHashCode.Include @Id private String email;
  private String name;

  private LocalDateTime createDate;
}
