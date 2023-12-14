package org.mklinkj.qna;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGetRealPathListener implements ServletContextListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestGetRealPathListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    LOGGER.info("### listener {}", sce.getServletContext().getRealPath("/WEB-INF/"));
  }
}
