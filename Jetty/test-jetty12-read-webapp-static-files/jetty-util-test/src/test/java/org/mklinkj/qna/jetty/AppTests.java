package org.mklinkj.qna.jetty;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import org.eclipse.jetty.util.resource.PathResource;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AppTests {
  @Disabled("To test, you must first create a junction link")
  @Test
  void testPathResource() throws Exception {
    PathResource aliasPath = App.getNewPathResource(Path.of("C:\\jetty-test\\hello-servlet"));
    assertThat(aliasPath.isAlias()).isTrue();

    PathResource realPath = App.getNewPathResource(Path.of("F:\\jetty-test\\hello-servlet"));
    assertThat(realPath.isAlias()).isFalse();
  }
}
