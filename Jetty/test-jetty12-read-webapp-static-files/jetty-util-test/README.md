# Check PathResource operation in my environment



* Request a test
  * https://github.com/jetty/jetty.project/issues/11077#issuecomment-1858565147

* Add dependency (version 12.0.4)

  ```groovy
  implementation "org.eclipse.jetty:jetty-util:${jettyVersion}"
  ```

* Run command

  ```sh
  gradlew clean run
  ```

* Result

  ```
  >gradlew clean run
  
  > Task :run
  pathA exists() = true
  pathA isDirectory() = true
  resourceA realPath() = F:\jetty-test\hello-servlet
  resourceA realURI() = file:///F:/jetty-test/hello-servlet/
  resourceA isAlias() = true
  pathB exists() = true
  pathB isDirectory() = true
  resourceB realPath() = F:\jetty-test\hello-servlet
  resourceB realURI() = file:///F:/jetty-test/hello-servlet/
  resourceB isAlias() = false
  
  BUILD SUCCESSFUL in 5s
  5 actionable tasks: 5 executed
  
  ```

  

