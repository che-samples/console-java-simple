import sbt._

object Dependencies {

  object Versions {

    val console4cats = "0.8.1"
    val scalaTest = "3.1.2"

  }

  val console4cats = "dev.profunktor" %% "console4cats" % Versions.console4cats
  val scalaTest = "org.scalatest" %% "scalatest" % Versions.scalaTest

}
