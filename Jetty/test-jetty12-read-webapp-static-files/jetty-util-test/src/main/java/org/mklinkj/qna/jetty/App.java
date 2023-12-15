package org.mklinkj.qna.jetty;

import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import org.eclipse.jetty.util.resource.PathResource;

public class App {

  public static void main(String[] args) throws Exception {

    Path pathA = Path.of("C:\\jetty-test\\hello-servlet");
    Path pathB = Path.of("F:\\jetty-test\\hello-servlet");

    PathResource resourceA = getNewPathResource(pathA);
    PathResource resourceB = getNewPathResource(pathB);

    System.out.println("pathA exists() = " + Files.exists(pathA));
    System.out.println("pathA isDirectory() = " + Files.isDirectory(pathA));
    System.out.println("resourceA realPath() = " + resourceA.getRealPath());
    System.out.println("resourceA realURI() = " + resourceA.getRealURI());
    System.out.println("resourceA isAlias() = " + resourceA.isAlias());
    System.out.println("pathB exists() = " + Files.exists(pathB));
    System.out.println("pathB isDirectory() = " + Files.isDirectory(pathB));
    System.out.println("resourceB realPath() = " + resourceB.getRealPath());
    System.out.println("resourceB realURI() = " + resourceB.getRealURI());
    System.out.println("resourceB isAlias() = " + resourceB.isAlias());
  }

  public static PathResource getNewPathResource(Path path) throws Exception {
    Class<?> clazz = Class.forName(PathResource.class.getCanonicalName());
    @SuppressWarnings("unchecked")
    Constructor<PathResource> constructor =
        (Constructor<PathResource>) clazz.getDeclaredConstructor(Path.class);
    constructor.setAccessible(true);
    return constructor.newInstance(path);
  }
}
