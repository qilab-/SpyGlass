val cdhVersion = "cdh5.3.0"

val hadoopVersion = "2.5.0-" + cdhVersion
val hadoopCoreVersion = "2.5.0-mr1-" + cdhVersion
val hbaseVersion = "0.98.6-" + cdhVersion
//val hbaseVersion = "0.94.10"

val jodaTimeVersion = "2.8.2"

val scaldingVersion = "0.16.0"
//val scaldingVersion = "0.12.0"
val scalatestVersion = "2.2.5"

val specs2Version = "3.7"
//val specs2Version = "2.1.1"
val typesafeConfigVersion = "1.0.0"

val junitVersion = "4.10"
val slf4jVersion = "1.7.2"

val sharedSettings = Seq(
  organization := "com.parallelai",

  scalaVersion := "2.11.7",
//  scalaVersion := "2.10.3",

  crossScalaVersions := Seq("2.10.6", "2.11.7"),

  javacOptions ++= Seq("-source", "1.6", "-target", "1.6"),
//  javacOptions ++= Seq("-source", "1.7", "-target", "1.7"),

  javacOptions in doc := Seq("-source", "1.6"),
//  javacOptions in doc := Seq("-source", "1.7"),

  libraryDependencies ++= Seq(
    "com.twitter" %% "scalding-core" % scaldingVersion,
    "org.apache.hadoop" % "hadoop-core" % hadoopCoreVersion,
    "org.apache.hadoop" % "hadoop-common" % hadoopVersion,
    "org.apache.hbase" % "hbase-common" % hbaseVersion,
    "org.apache.hbase" % "hbase-client" % hbaseVersion,
    "org.apache.hbase" % "hbase-server" % hbaseVersion,
    //"org.apache.hbase" % "hbase" % hbaseVersion,
    "org.slf4j" % "slf4j-api" % slf4jVersion,
    "com.typesafe" % "config" % typesafeConfigVersion,
    "joda-time" % "joda-time" % jodaTimeVersion,
    "com.twitter.elephantbird" % "elephant-bird-core" % "4.1",
    "com.twitter.elephantbird" % "elephant-bird-hadoop-compat" % "4.1",
    "org.specs2" %% "specs2" % specs2Version % "test",
    "org.scalatest" %% "scalatest" % scalatestVersion % "test",
    "junit" % "junit" % junitVersion % "test",
    "org.apache.hbase" % "hbase-hadoop-compat" % hbaseVersion % "test",
    "org.apache.hbase" % "hbase-hadoop2-compat" % hbaseVersion % "test"
  ),

//  dependencyOverrides += "org.scala-lang" % "scala-library" % scalaVersion.value,

  resolvers ++= Seq(
    "Cloudera" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
    "Concurrent Maven Repo" at "http://conjars.org/repo",
    "Twitter Maven" at "http://maven.twttr.com"
  )
)

lazy val spyglass = Project(
  id = "SpyGlass",
  base = file("."),
  settings = sharedSettings
)
