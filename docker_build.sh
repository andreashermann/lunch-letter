#!/bin/bash
cp target/scala-2.11/mailer-assembly-1.0.jar .
docker build -t lunchletter/mailer .
#rm mailer-assembly-1.0.jar
