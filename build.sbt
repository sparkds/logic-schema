import sbt.url

ThisBuild / scalaVersion := "2.12.12"
ThisBuild / version := "0.0.2"
ThisBuild / organization := "com.github.sparkds"
ThisBuild / homepage := Option(url("https://github.com/sparkds/logic-schema"))
ThisBuild / licenses := Seq("Apache-2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0"))
ThisBuild / description := """sparkds/logic-schema is a Scala library for data specification and validation across dataframes(hence logic schema), to help developing applications based on Apache Spark."""
ThisBuild / scmInfo := Option(ScmInfo(
  url("https://github.com/sparkds/logic-schema"),
  "scm:git@github.com:sparkds/logic-schema.git"))
ThisBuild / developers := List(
  Developer(
    id    = "dj707chen",
    name  = "DJ Chen",
    email = "@dj707chen",
    url   = url("https://github.com/dj707chen")
  )
)

val sonatypeSettings: Seq[Def.Setting[_]] = Seq(
  publishArtifact in Test := false,
//resolvers ++= Seq("sonatype-public" at "https://s01.oss.sonatype.org/content/repositories/public"),
  resolvers ++= Seq("sonatype-public" at "https://oss.sonatype.org/content/repositories/public"),
  publishTo := {
    val ver = version.value
//  val nexus = "https://s01.oss.sonatype.org/"
    val nexus = "https://oss.sonatype.org/"
    if (ver.trim.endsWith("SNAPSHOT")) {
      Some("snapshots" at nexus + "content/repositories/snapshots")
    } else {
      Some("releases" at nexus + "service/local/staging/deploy/maven2")
    }
  }
)

lazy val commonSettings = Seq(
  scalacOptions := Seq("-deprecation", "-unchecked", "-feature", "-language:implicitConversions", "-language:postfixOps"),
  Test / parallelExecution := false,
  // resolvers += Resolver.typesafeIvyRepo("releases"),
  // Adds a `src/test/scala-2.13+` source directory for Scala 2.13 and newer
  // and a `src/test/scala-2.13-` source directory for Scala version older than 2.13
  Test / unmanagedSourceDirectories += {
    val sourceDir = (Test / sourceDirectory).value
    CrossVersion.partialVersion(scalaVersion.value) match {
      case Some((2, n)) if n >= 13 => sourceDir / "scala-2.13+"
      case _ => sourceDir / "scala-2.13-"
    }
  }
) ++ sonatypeSettings


val sparkVersion = "2.4.4"
val sparkSQL = "org.apache.spark" %% "spark-sql" % sparkVersion
val sparkCore = "org.apache.spark" %% "spark-core" % sparkVersion
val scalaCheck = "org.scalacheck" %% "scalacheck" % "1.15.2"

val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % Test

lazy val logic_schema = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "logic-schema",
    libraryDependencies ++= Seq(sparkCore, sparkSQL, scalaCheck, scalaTest)
  )

