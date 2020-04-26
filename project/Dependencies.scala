import sbt._

object Dependencies {

  object Versions {

    val betterMonadicFor = "0.3.1"
    val console4cats = "0.8.1"
    val kindProjector = "0.10.3"
    val organizeImports = "0.2.1"
    val scalaTest = "3.1.1"
    val scaluzzi = "0.1.6"
    val silencer = "1.6.0"

  }

  val betterMonadicFor = "com.olegpy" %% "better-monadic-for" % Versions.betterMonadicFor
  val console4cats = "dev.profunktor" %% "console4cats" % Versions.console4cats
  val kindProjector = "org.typelevel" %% "kind-projector" % Versions.kindProjector
  val organizeImports = "com.github.liancheng" %% "organize-imports" % Versions.organizeImports
  val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest
  val scaluzzi = "com.github.vovapolu" %% "scaluzzi" % Versions.scaluzzi
  val silencer = "com.github.ghik" % "silencer-plugin" % Versions.silencer cross CrossVersion.full
  val silencerLib = "com.github.ghik" % "silencer-lib" % Versions.silencer % Provided cross CrossVersion.full

}
