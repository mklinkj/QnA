plugins {
  java
}

buildscript {
  val lombokPluginVersion = project.findProperty("lombokPluginVersion") as String
  val springBootPluginVersion = project.findProperty("springBootPluginVersion") as String
  val springDependencyManagementVersion = project.findProperty("springDependencyManagementVersion") as String
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("io.freefair.gradle:lombok-plugin:${lombokPluginVersion}")
    classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootPluginVersion")
    classpath("io.spring.gradle:dependency-management-plugin:$springDependencyManagementVersion")
  }
}

apply(plugin = "io.freefair.lombok")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

group = "org.mklinkj.qna.spring_batch"
version = "0.0.1-SNAPSHOT"

java {
  sourceCompatibility = JavaVersion.valueOf(project.findProperty("javaVersion") as String)
}

repositories {
  mavenCentral()
}

extra["hsqldb.version"] = project.findProperty("hsqldbVersion") as String

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-batch")
  implementation("org.springframework.boot:spring-boot-starter-jdbc")
  runtimeOnly("org.hsqldb:hsqldb")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.springframework.batch:spring-batch-test")
  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    outputs.upToDateWhen { false }
    showStandardStreams = true
  }
}
