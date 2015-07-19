#!/bin/bash
set -x
docker stop scheduler-prod 
docker rm scheduler-prod 
docker run -d --name scheduler-prod lunchletter/schedulers
