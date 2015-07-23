name := "mailer"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "javax.mail" % "javax.mail-api" % "1.5.4"

libraryDependencies += "com.sun.mail" % "javax.mail" % "1.5.4"

libraryDependencies += "io.prediction" % "client" % "0.8.3"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"

libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test"