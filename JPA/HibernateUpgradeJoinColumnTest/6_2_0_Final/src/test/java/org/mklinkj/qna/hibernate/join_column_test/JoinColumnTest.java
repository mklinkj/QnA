package org.mklinkj.qna.hibernate.join_column_test;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mklinkj.qna.hibernate.common.jpa.EMF;
import org.mklinkj.qna.hibernate.join_column_test.entity.MembershipCard;
import org.mklinkj.qna.hibernate.test.util.DBTestResourceExtension;

/** JoinColumn Test */
@Slf4j
@ExtendWith(DBTestResourceExtension.class)
class JoinColumnTest {
  @Test
  void countMembershipCard() {
    EMF.execute(
        em -> {
          TypedQuery<MembershipCard> query =
              em.createQuery(
                  """
              SELECT mc
                FROM MembershipCard mc
              """,
                  MembershipCard.class);

          List<MembershipCard> cardList = query.getResultList();
          LOGGER.info("cardList: {}", cardList);
          assertThat(cardList).isNotEmpty().hasSize(3);
        });
  }
}
