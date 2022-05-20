ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

libraryDependencies += "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4"

// Cats
val catsV = "2.3.0"
libraryDependencies += "org.typelevel" %% "cats-core" % catsV
libraryDependencies += "org.typelevel" %% "cats-laws" % catsV
libraryDependencies += "org.typelevel" %% "cats-testkit" % catsV
libraryDependencies += "org.typelevel" %% "cats-effect" % catsV
libraryDependencies += "org.typelevel" %% "spire" % "0.17.0"
libraryDependencies += "org.typelevel" %% "kittens" % catsV

// Test
libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.1" % Test
libraryDependencies += "com.github.alexarchambault" %% "scalacheck-shapeless_1.14" % "1.2.3" % Test
libraryDependencies += "org.typelevel" %% "discipline-scalatest" % "2.1.5" % Test

lazy val root = (project in file("."))
  .settings(
    name := "monad"
  )
