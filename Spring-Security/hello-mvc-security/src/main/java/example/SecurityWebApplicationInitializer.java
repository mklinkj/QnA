/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package example;

import jakarta.servlet.ServletContext;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * We customize {@link AbstractSecurityWebApplicationInitializer} to enable the {@link
 * HttpSessionEventPublisher}.
 *
 * @author Rob Winch
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

  @Override
  protected boolean enableHttpSessionEventPublisher() {
    return true;
  }

  @Override
  protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
    // When submitting a form, set additional filters
    // to make requests other than GET and POST using the _method Hidden input.
    //
    // * The HiddenHttpMethodFilter filter determines the request method by
    //   looking at the `_method` Hidden input value received in the Form POST request,
    //   so it must be located before the Spring Security FilterChain.
    servletContext
        .addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter())
        .addMappingForUrlPatterns(null, false, "/*");
  }
}
