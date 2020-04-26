ThisBuild / scalaVersion := "2.12.11"
ThisBuild / organization := "org.eclipse.che.examples"
ThisBuild / organizationName := "examples"

lazy val root = project
  .in(file("."))
  .settings(
    name := "console-scala-simple",
    publish / skip := true,
  )
  .aggregate(example)

lazy val example = project
  .in(file("example"))
  .settings(commonSettings)
  .settings(
    name := "example",
    libraryDependencies ++= List(
      Dependencies.console4cats,
      Dependencies.scalaTest % Test,
    ),
  )

lazy val commonSettings: List[Def.Setting[_]] = List(
  libraryDependencies ++= List(
    compilerPlugin(Dependencies.betterMonadicFor),
    compilerPlugin(Dependencies.kindProjector),
    compilerPlugin(Dependencies.silencer),
    Dependencies.silencerLib,
  ),
  semanticdbEnabled := true, // enable SemanticDB
  semanticdbVersion := scalafixSemanticdb.revision, // use Scalafix compatible version
  ThisBuild / scalafixDependencies ++= List(
    Dependencies.organizeImports,
    Dependencies.scaluzzi,
  ),
  scalacOptions ++= Seq(
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
