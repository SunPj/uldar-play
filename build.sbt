name := """play-uldar-cms-integration"""
organization := "com.github.sunpj.uldarblog.play"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.1"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test,
  "com.github.sunpj" %% "uldar" % "0.1-SNAPSHOT"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.github.sunpj.uldarblog.play.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.github.sunpj.uldarblog.play.binders._"
