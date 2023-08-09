package org.mklinkj.qna.primitive_pk.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mklinkj.qna.test.DatabaseInitExtension;

@ExtendWith(DatabaseInitExtension.class)
@Slf4j
class EmployeeTests {
  @Test
  void deleteEmployee() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PRIMITIVE_PK");
    EntityManager em = emf.createEntityManager();

    Employee employee = em.find(Employee.class, 0L);
    LOGGER.info("1st employee id: 0: {}", employee);
    assertThat(employee).isNotNull();

    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.remove(employee);
      tx.commit();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      tx.rollback();
      fail();
    }

    employee = em.find(Employee.class, 0L);
    LOGGER.info("2nd employee id: 0: {}", employee);
    assertThat(employee).isNull();

    em.close();
    emf.close();
  }
}
