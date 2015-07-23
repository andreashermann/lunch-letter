#!/bin/bash
set -x
docker stop mailer-prod 
docker rm mailer-prod 
docker run -d --name mailer-prod lunchletter/mailer
