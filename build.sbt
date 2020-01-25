ThisBuild / scalaVersion := "2.12.10"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "org.eclipse.che.examples"
ThisBuild / organizationName := "examples"

lazy val root = (project in file("."))
  .settings(commonSettings)
  .settings(
    name := "console-scala-simple",
    libraryDependencies ++= List(
      Dependencies.console4cats,
      Dependencies.scalaTest % Test,
    ),
  )

lazy val commonSettings: List[Def.Setting[_]] = List(
  libraryDependencies ++= List(
    compilerPlugin(Dependencies.kindProjector),
    compilerPlugin(Dependencies.silencer),
    compilerPlugin(scalafixSemanticdb),
    Dependencies.silencerLib,
  ),
  ThisBuild / scalafixDependencies ++= List(
    Dependencies.sortImports,
  ),
  scalacOptions ++= Seq(
    "-Yrangepos",
    "-Ywarn-unused",
    "-P:silencer:checkUnused",
  ),
)

addCommandAlias(
  "check",
  "; scalafmtSbtCheck; scalafmtCheckAll; compile:scalafix --check; test:scalafix --check",
)
addCommandAlias(
  "fix",
  "; compile:scalafix; test:scalafix; scalafmtSbt; scalafmtAll",
)
