name := "hello"

version := "1.0"

scalaVersion := "2.12.6"

sbtVersion := "1.2.3"

//seq(webSettings : _*)
resolvers ++= Seq(
  "snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "releases" at "http://oss.sonatype.org/content/repositories/releases"
)


libraryDependencies += "org.springframework.boot" % "spring-boot-starter" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-test" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-web" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-thymeleaf" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-jdbc" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-data-jpa" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-security" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-data-mongodb" % "2.0.2.RELEASE"

libraryDependencies += "org.springframework.boot" % "spring-boot-autoconfigure" % "2.0.2.RELEASE"

// libraryDependencies += "org.springframework.boot" % "spring-boot-starter-actuator" % "2.0.2.RELEASE"




// libraryDependencies += "org.hibernate" % "hibernate-core" % "5.3.7.Final"

libraryDependencies += "com.h2database" % "h2" % "1.4.197"



mainClass in (Compile,run) := Some("com.example.mongo.Application")
