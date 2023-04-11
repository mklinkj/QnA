package org.mklinkj.qna.hibernate.join_column_test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
public class MembershipCard {
  @Id
  @Column(name = "card_number")
  private String number;

  @ManyToOne
  @JoinColumn(name = "user_email")
  private Student owner;

  private LocalDateTime expiryDate;

  private boolean enabled;
}
