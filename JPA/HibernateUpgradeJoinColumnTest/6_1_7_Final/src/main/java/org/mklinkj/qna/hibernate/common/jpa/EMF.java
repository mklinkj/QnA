package org.mklinkj.qna.hibernate.common.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.function.Consumer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EMF {

  private static final ThreadLocal<EntityManager> currentEntityManager = new ThreadLocal<>();
  private static EntityManagerFactory entityManagerFactory;

  public static void init() {
    entityManagerFactory = Persistence.createEntityManagerFactory("jpastart");
  }

  public static EntityManager createEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  public static void close() {
    entityManagerFactory.close();
  }

  public static boolean hasEntityManager() {
    EntityManager em = currentEntityManager.get();
    return em != null;
  }

  public static EntityManager currentEntityManager() {
    EntityManager em = currentEntityManager.get();
    if (!hasEntityManager()) {
      em = createEntityManager();
      currentEntityManager.set(em);
    }
    return em;
  }

  public static void closeCurrentEntityManager() {
    EntityManager em = currentEntityManager.get();
    if (em != null) {
      currentEntityManager.remove();
      em.close();
    }
  }

  public static void execute(Consumer<EntityManager> c) {
    final boolean hasEntityManager = hasEntityManager();
    final EntityManager em = currentEntityManager();
    try {
      c.accept(em);
    } finally {
      if (!hasEntityManager) {
        closeCurrentEntityManager();
      }
    }
  }
}
