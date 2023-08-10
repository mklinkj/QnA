package org.mklinkj.qna.primitive_pk.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mklinkj.qna.primitive_pk.config.RootConfig;
import org.mklinkj.qna.primitive_pk.repository.EmployeeRepository;
import org.mklinkj.qna.test.DatabaseInitExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith({DatabaseInitExtension.class})
@SpringJUnitConfig(classes = RootConfig.class)
@Slf4j
class EmployeeTests {
  @Autowired private EmployeeRepository repository;

  @Test
  void deleteEmployee() {
    // 1. Querying members with ID 0
    Optional<Employee> result = repository.findById(0);
    assertThat(result.isPresent()).isTrue();
    Employee employee = result.get();
    LOGGER.info("Before delete. / employee id: 0: {}", employee);

    // 2. Delete employee with id 0
    //    If Employee's id type is Integer, deletion works correctly
    repository.delete(employee);
    repository.flush();

    // ~~3. A member with ID 0 exists, because it was not deleted from `2.`~~
    // 엔티티에 Persistable 을 구현해서 다시 동작 확인했을 때.. 잘 동작함.
    // 그런데. -1일 때 새 앤티티가 된다는 전제를 가지게 됨.
    result = repository.findById(0);
    assertThat(result.isPresent()).isFalse();
  }
}
