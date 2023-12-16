package org.mklinkj.qna;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    request.setAttribute("message", "Hello, Jetty 12 👍");

    RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/jsp/helloworld.jsp");
    dispatch.forward(request, response);
  }
}
