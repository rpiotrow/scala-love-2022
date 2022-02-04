lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-love-2022",
    description := "Sample code for Scala Love 2022 talk: New types as a solution to primitive obsession",
    version := "0.1.0-SNAPSHOT"
  )
  .aggregate(scala3, scala2)

lazy val scala3 = project
  .in(file("scala3"))
  .settings(
    scalaVersion := "3.1.1",

    libraryDependencies ++= Seq(
      "io.monix"      %% "newtypes-core"       % "0.2.1",
      "dev.zio"       %% "zio-prelude"         % "1.0.0-RC10",
      "pl.iterators"  %% "kebs-opaque"         % "1.9.4",
      "eu.timepit"    %% "refined"             % "0.9.28",
      "com.abdulradi" %% "mazboot-types"       % "0.5.0",
      "org.scalatest" %% "scalatest-funsuite"  % "3.2.11"       % Test
    )
  )

lazy val scala2 = project
  .in(file("scala2"))
  .settings(
    scalaVersion := "2.13.8",

    libraryDependencies ++= Seq(
      "pl.iterators"  %% "kebs-tagged"         % "1.9.4",
      "pl.iterators"  %% "kebs-tagged-meta"    % "1.9.4",
      "io.estatico"   %% "newtype"             % "0.4.4",
      "eu.timepit"    %% "refined"             % "0.9.28"
    ),
    scalacOptions += "-Ymacro-annotations"
  )
