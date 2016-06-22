import play.sbt.PlayImport._
import sbt._

object Versions {
  val scala          = "2.11.8"
  val play           = "2.5.4"
  val akka           = "2.3.12"
  val elastic4s      = "1.7.4"
  val util           = "0.15.0"
  val phantom        = "1.24.0"
  val log4j          = "2.3"
  val slf4j          = "1.6.4"
  val scalatest      = "2.2.4"
}

object Dependencies {
  import Resolver.{bintrayRepo, sonatypeRepo, typesafeRepo}

  val sharedResolvers = Seq(
    JavaNet2Repository,
    Opts.resolver.mavenLocalFile,
    sonatypeRepo("releases"),
    sonatypeRepo("snapshots"),
    sonatypeRepo("staging"),
    typesafeRepo("snapshots"),
    bintrayRepo("scalaz",    "releases"),
    bintrayRepo("websudos",  "oss-releases")
  )

  val webjars = Seq(
    "org.webjars"             %% "webjars-play"             % "2.4.0-1",
    "org.webjars.bower"       % "compass-mixins"            % "1.0.2",
    "org.webjars"             % "bootstrap"                 % "4.0.0-alpha.2",
    //angular2 dependencies
    //"org.webjars.npm"       % "angular2" % "2.0.0-beta.17",
    "org.webjars.npm"         % "zcourts-angular-master"    % "2.0.0-rc.2-master",
    "org.webjars.npm"         % "systemjs"                  % "0.19.31",
    "org.webjars.npm"         % "rxjs"                      % "5.0.0-beta.7",
    "org.webjars.npm"         % "es6-promise"               % "3.1.2",
    "org.webjars.npm"         % "es6-shim"                  % "0.35.0",
    "org.webjars.npm"         % "reflect-metadata"          % "0.1.3",
    "org.webjars.npm"         % "zone.js"                   % "0.6.12",
    "org.webjars.npm"         % "typescript"                % "1.9.0-dev.20160516",
    //tslint dependency
    "org.webjars.npm"         % "tslint-eslint-rules"       % "1.2.0",
    "org.webjars.npm"         % "codelyzer"                 % "0.0.19",
    //test
    "org.webjars.npm"         % "jasmine"                   % "2.4.1" % "test"
  )

  val akka = Seq(
    "com.typesafe.akka"       %% "akka-actor"               % Versions.akka,
    "com.typesafe.akka"       %% "akka-agent"               % Versions.akka,
    "com.typesafe.akka"       %% "akka-slf4j"               % Versions.akka,
    "com.typesafe.akka"       %% "akka-testkit"             % Versions.akka % "it, test"
  )

  val kafka = Seq(
    "org.apache.kafka"        %% "kafka"                    % "0.8.2.1"
      exclude("org.slf4j", "slf4j-log4j12")
      exclude("org.slf4j", "slf4j-simple")
      exclude("log4j", "log4j")
      exclude("javax.jms", "jms")
      exclude("com.sun.jdmk", "jmxtools")
      exclude("com.sun.jmx", "jmxri")
  )

  val shared = Seq(
    "org.scalatest"            %% "scalatest"               % Versions.scalatest % "it, test, provided"
  )


  val forClient = Seq(
    "org.scala-js"             %% "scalajs-dom"             % "0.8.0",
    "org.scalatest"            %% "scalatest"               % Versions.scalatest % "it, test, provided"
  )

  val forServer = Seq(
    jdbc , cache , ws,
    "com.typesafe"             %  "config"                  % "1.3.0",
    "com.typesafe.akka"        %% "akka-actor"              % Versions.akka,
    "com.typesafe.play"        %% "play-json"               % Versions.play excludeAll ExclusionRule("com.chuusai", "shapeless"),
    "com.typesafe.play"        %% "play-mailer"             % "4.0.0-M1",
    "com.typesafe.play"        %% "play-json"               % Versions.play,
    "net.codingwell"           %% "scala-guice"             % "4.0.0",
    "com.websudos"             %% "phantom-dsl"             % Versions.phantom,
    "com.sksamuel.elastic4s"   %% "elastic4s-core"          % Versions.elastic4s,
    "com.websudos"             %% "util-testing"            % Versions.util % "test, provided",
    "org.slf4j"                %  "slf4j-api"               % "1.7.10",
    "org.slf4j"                %  "log4j-over-slf4j"        % "1.7.7",
    "org.scalatest"            %% "scalatest"               % Versions.scalatest % "it, test, provided"
  )
}
