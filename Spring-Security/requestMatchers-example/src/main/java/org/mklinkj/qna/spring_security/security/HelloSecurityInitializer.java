package org.mklinkj.qna.spring_security.security;

import javax.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Slf4j
public class HelloSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

  public HelloSecurityInitializer() {
    super(HelloSecurityConfig.class);
  }

  @Override
  protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
    LOGGER.info("### ServerInfo: {} ###", servletContext.getServerInfo()); // Tomcat 9.0.82
    LOGGER.info(
        "### EffectiveMajorVersion: {} ###", servletContext.getEffectiveMajorVersion()); // 4
  }
}
