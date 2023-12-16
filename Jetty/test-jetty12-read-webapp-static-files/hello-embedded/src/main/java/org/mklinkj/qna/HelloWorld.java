package org.mklinkj.qna;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.ee8.servlet.ServletContextHandler;
import org.eclipse.jetty.ee8.servlet.ServletHolder;
import org.eclipse.jetty.server.Server;

public class HelloWorld extends HttpServlet {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);

    context.addServlet(new ServletHolder(new HelloWorld()), "/*");

    server.start();
    server.join();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    resp.setContentType("text/html;charset=utf-8");
    resp.setStatus(HttpServletResponse.SC_OK);
    resp.getWriter().println("<h1>Hello World</h1>");
  }
}
