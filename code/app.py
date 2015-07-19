import os

import schedule

import smtplib
from email.mime.text import MIMEText

FROM = os.environ['EMAIL_FROM']
SERVER = os.environ['EMAIL_SERVER']
USERNAME = os.environ['EMAIL_USERNAME']
PASSWORD = os.environ['EMAIL_PASSWORD']

def send_email(recipient):
    message = MIMEText("Hello from Python")
    message['Subject'] = "Lunch Letter Recommendations"
    message['From'] = FROM
    message['To'] = recipient

    server = smtplib.SMTP(SERVER)
    server.starttls()
    server.login(USERNAME, PASSWORD)
    server.sendmail(FROM, recipient, message.as_string())
    server.quit()

if __name__ == "__main__":
    send_email(recipient='a.v.hermann@gmail.com')
