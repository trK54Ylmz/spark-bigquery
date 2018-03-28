name := "spark-bigquery"
version := "0.1"
scalaVersion := "2.11.8"

parallelExecution in Test := false

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-sql" % "2.1.0",
  "org.apache.spark" %% "spark-hive" % "2.1.0" % "test",
  "com.google.cloud.bigdataoss" % "bigquery-connector" % "0.8.0-hadoop2"
    exclude("com.google.guava", "guava-jdk5"),
  "joda-time" % "joda-time" % "2.9.3",
  "commons-codec" % "commons-codec" % "1.10",
  "org.mockito" % "mockito-core" % "1.8.5" % "test",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test"
)