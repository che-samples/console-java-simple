import sbt._

object Dependencies {

  object Versions {

    val console4cats = "0.8.1"
    val kindProjector = "0.10.3"
    val scalaTest = "3.1.0"
    val silencer = "1.4.4"
    val sortImports = "0.3.2"

  }

  val console4cats = "dev.profunktor" %% "console4cats" % Versions.console4cats
  val kindProjector = "org.typelevel" %% "kind-projector" % Versions.kindProjector
  val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  val silencer = "com.github.ghik" % "silencer-plugin" % Versions.silencer cross CrossVersion.full
  val silencerLib = "com.github.ghik" % "silencer-lib" % Versions.silencer cross CrossVersion.full
  val sortImports = "com.nequissimus" %% "sort-imports" % Versions.sortImports

}
