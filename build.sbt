enablePlugins(JDebPackaging)

name := "TwaaRecommender"

version := "0.1"

scalaVersion := "2.11.12"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
scalacOptions ++= Seq("-target:jvm-1.8", "-language:postfixOps")

daemonUser in Linux := "twaa"

maintainer := "Michael Gikaru <thealmikey@gmail.com>"

packageSummary := "Twaa GraphDb and recommender system"

mainClass in Compile := Some("com.neo4twaa.TwaaRecoMain")

packageDescription :=
  """
    |A System to help save users and recommendations
    |for TWAA
  """.stripMargin

libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.9"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.23"

val circeVersion = "0.10.0"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

libraryDependencies += "io.circe" %% "circe-optics" % circeVersion
// https://mvnrepository.com/artifact/de.heikoseeberger/akka-http-circe
libraryDependencies += "de.heikoseeberger" %% "akka-http-circe" % "1.27.0"

libraryDependencies ++= Seq(
  "org.neo4j" % "neo4j-ogm-core" % "3.1.11",
  "org.neo4j" % "neo4j-ogm-http-driver" % "3.1.11",
  "org.neo4j" % "neo4j-ogm-bolt-driver" % "3.1.11",
  "org.neo4j" % "neo4j-ogm-embedded-driver" % "3.1.11"
)

// https://mvnrepository.com/artifact/org.neo4j.driver/neo4j-java-driver
libraryDependencies += "org.neo4j.driver" % "neo4j-java-driver" % "2.0.0-alpha03"

addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)


libraryDependencies += "com.google.code.gson" % "gson" % "2.8.5"