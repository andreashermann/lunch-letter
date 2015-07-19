FROM python:2.7
RUN apt-get update && apt-get install -y python-pip
COPY requirements.txt /opt/lunchletter-scheduler/
RUN cd /opt/lunchletter-scheduler && pip install -r requirements.txt
COPY code /opt/lunchletter-scheduler/
CMD python -u /opt/lunchletter/code/app.py
