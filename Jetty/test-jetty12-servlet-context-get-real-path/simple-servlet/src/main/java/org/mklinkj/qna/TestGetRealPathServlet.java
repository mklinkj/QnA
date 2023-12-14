package org.mklinkj.qna;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGetRealPathServlet extends HttpServlet {
  private static final Logger LOGGER = LoggerFactory.getLogger(TestGetRealPathServlet.class);

  private ServletContext context;

  @Override
  public void init(ServletConfig config) {
    context = config.getServletContext();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.getWriter().println(getClass().getSimpleName());
    var realPath = context.getRealPath("/WEB-INF/");
    LOGGER.info("### servlet: {}", realPath);
    response.getWriter().println(realPath);
  }
}
