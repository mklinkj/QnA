package org.mklinkj.qna;

import org.apache.tomcat.util.scan.StandardJarScanFilter;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.eclipse.jetty.ee8.apache.jsp.JettyJasperInitializer;
import org.eclipse.jetty.ee8.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.SymlinkAllowedResourceAliasChecker;

public class HelloWorldWebApp {

  public static void main(String[] args) throws Exception {
    var server = new Server(8080);

    var context = new WebAppContext();
    context.setContextPath("/");
    context.setBaseResourceAsString("src/main/webapp");
    context.addAliasCheck(new SymlinkAllowedResourceAliasChecker(context.getCoreContextHandler()));

    var jarScanner = new StandardJarScanner();
    var jarScanFilter = new StandardJarScanFilter();
    jarScanFilter.setTldScan("javax.servlet.jsp.jstl-*");
    jarScanFilter.setDefaultTldScan(false);

    jarScanner.setJarScanFilter(jarScanFilter);
    context.setAttribute("org.apache.tomcat.JarScanner", jarScanner);
    new JettyJasperInitializer().onStartup(null, context.getServletContext());

    server.setHandler(context);
    server.start();
    server.join();
  }
}
