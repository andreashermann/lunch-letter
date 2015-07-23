FROM java:8
COPY target/scala-2.11/mailer-assembly-1.0.jar /opt/lunchletter-mailer/
CMD java -jar /opt/lunchletter-mailer/mailer-assembly-1.0.jar