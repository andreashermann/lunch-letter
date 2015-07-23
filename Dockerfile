FROM java:8
COPY mailer-assembly-1.0.jar /opt/lunchletter-mailer/
CMD java -jar /opt/lunchletter-mailer/mailer-assembly-1.0.jar
