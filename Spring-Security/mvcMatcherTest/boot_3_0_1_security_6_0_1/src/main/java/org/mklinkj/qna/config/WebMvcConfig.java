package org.mklinkj.qna.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-3.0-Migration-Guide#web-application-changes
// Spring 6 부터 알아서 후행 슬레시를 자동으로 처리해주지 않음.
// 아래 설정은 없어질 예정이고, 명시적으로 설정을 권장하는 것 같다.
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.setUseTrailingSlashMatch(true);
  }
}
