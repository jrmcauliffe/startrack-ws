import ScalaxbKeys._

name := "Startrack API"

version := "1.0.0"

organization := "org.birchavenue"

scalaVersion := "2.9.1"

resolvers ++= Seq("snapshots"     at "http://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "http://oss.sonatype.org/content/repositories/releases"
                )

libraryDependencies ++= Seq("net.databinder.dispatch" %% "dispatch-core"          % "0.9.5",
"ch.qos.logback"          % "logback-classic"         % "1.0.6",
"org.scalaj" %% "scalaj-time" % "0.6")

scalacOptions ++= Seq("-deprecation", "-unchecked")

//scalaxbSettings

//packageName in scalaxb in Compile := "org.birchavenue.startrack"

//protocolPackageName in scalaxb in Compile := Some("org.birchavenue.startrack")


//sourceGenerators in Compile <+= scalaxb in Compile
