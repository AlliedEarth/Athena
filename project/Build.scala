import com.typesafe.sbt.jse.JsEngineImport.JsEngineKeys
import org.scalajs.sbtplugin.ScalaJSPlugin
import play.sbt.PlayScala
import com.vmunier.....
import name.devries.sbt.typescript.SbtTypescript
import name.devries.sbt.typescript.SbtTypescript.autoImport._
import name.devries.tslint.SbtTSLint.autoImport._
import play.sbt.routes.RoutesKeys._
import sbt._
import Keys._

object Build extends Build {

  val sharedSettings: Seq[Def.Setting[_]] = Seq(
    scalaVersion := Versions.scala,
    ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) },
    organization := "earth.allied",
    libraryDependencies ++= Dependencies.shared,
    scalacOptions ++= Seq(
      "-deprecation",
      "-encoding", "UTF-8",
      "-feature",
      "-language:implicitConversions",
      "-language:postfixOps",
      "-Xlint",
      "-Ywarn-value-discard"
    ),
    javaOptions in ThisBuild ++= Seq(
      "-Xms1G",
      "-Xmx4G",
      "-XX:+UseConcMarkSweepGC"
    ),
    resolvers ++= Dependencies.sharedResolvers,
    parallelExecution in Test := false,
    fork in run  := true,
    fork in test := true,
    JsEngineKeys.engineType := JsEngineKeys.EngineType.Node
  ) ++ Defaults.coreDefaultSettings ++ Defaults.itSettings


  lazy val commonConfig: Project => Project =   _ configs IntegrationTest settings sharedSettings

  lazy val `AlliedEarth` = (project in file(".")
      configure commonConfig
      settings (parallelExecution in Test := false)
      aggregate (
      shared,
      client,
      server
      )
    )

  lazy val shared = (project
      in file("shared")
      configure commonConfig
      settings(libraryDependencies ++= Dependencies.shared)
    )

  lazy val client = (project
      in file("client")
      dependsOn shared
      enablePlugins (ScalaJSPlugin, ScalaJSPlay)
      configure commonConfig
      settings(libraryDependencies ++= Dependencies.forClient)
    )

  lazy val server = (project
      in file("server")
      dependsOn client
      enablePlugins (PlayScala, SbtTypescript)
      configure commonConfig
      settings(libraryDependencies ++= Dependencies.forServer)
    )

  // the typescript typing information is by convention in the typings directory
  // It provides ES6 implementations. This is required when compiling to ES5.
  //typingsFile = Some(baseDirectory.value / "typings" / "index.d.ts")

  // use the webjars npm directory (target/web/node_modules ) for resolution of module imports of angular2/core etc
  resolveFromWebjarsNodeModulesDir := true

  // use the combined tslint and eslint rules plus ng2 lint rules
  (rulesDirectories in tslint) := Some(List(tslintEslintRulesDir.value,ng2LintRulesDir.value))

  routesGenerator := InjectedRoutesGenerator

  offline := true

  unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

  // loads the Play project at sbt startup
  onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
}
