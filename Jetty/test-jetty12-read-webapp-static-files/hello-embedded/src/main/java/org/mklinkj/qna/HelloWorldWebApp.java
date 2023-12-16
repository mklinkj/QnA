package org.mklinkj.qna;

import org.eclipse.jetty.ee8.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SymlinkAllowedResourceAliasChecker;

public class HelloWorldWebApp {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8080);

    WebAppContext context = new WebAppContext();
    context.setContextPath("/");
    context.setBaseResourceAsString("src/main/webapp");
    context.addAliasCheck(new SymlinkAllowedResourceAliasChecker(context.getCoreContextHandler()));
    server.setHandler(context);
    server.start();
    server.join();
  }
}
