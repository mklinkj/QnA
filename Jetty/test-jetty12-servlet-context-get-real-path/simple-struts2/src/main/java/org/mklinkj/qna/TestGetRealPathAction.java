package org.mklinkj.qna;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.ServletContext;
import org.apache.struts2.action.ServletContextAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGetRealPathAction extends ActionSupport implements ServletContextAware {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestGetRealPathAction.class);
  private ServletContext servletContext;

  private String realPath;

  @Override
  public String execute() {
    this.realPath = this.servletContext.getRealPath("/WEB-INF/");
    LOGGER.info("### realPath: {}", realPath);
    return SUCCESS;
  }

  @Override
  public void withServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @SuppressWarnings("unused")
  public String getRealPath() {
    return realPath;
  }
}
