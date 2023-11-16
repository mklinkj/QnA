package org.mklinkj.qna.spring_security.security;

import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Slf4j
public class HelloSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

  public HelloSecurityInitializer() {
    super(HelloSecurityConfig.class);
  }

  @Override
  protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
    LOGGER.info("### ServerInfo: {} ###", servletContext.getServerInfo()); // Tomcat 10.1.x
    LOGGER.info(
        "### EffectiveMajorVersion: {} ###", servletContext.getEffectiveMajorVersion()); // 6
  }
}
