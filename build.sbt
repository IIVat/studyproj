name := "studyproj"

version := "0.1"

scalaVersion := "3.0.0"
crossScalaVersions ++= Seq("2.13.5", "3.0.0")

libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-effect" % "3.1.1",
  "org.typelevel" %% "cats-core" % "2.6.1"
//  "org.typelevel" %% "cats-tagless" % "2.6.1"
//  "dev.zio" %% "zio" % "1.0.7",
//  "dev.zio" %% "zio-streams" % "1.0.7"
)
