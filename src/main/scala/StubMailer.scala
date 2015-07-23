import java.util.Properties
import javax.mail.Message.RecipientType
import javax.mail.internet.MimeMessage
import javax.mail.{Authenticator, PasswordAuthentication, Session}

import main.scala.SmtpMailer

class StubMailer extends SmtpMailer {

  override def send(recipient: String, subject: String, body: String): Unit = {
    print("sending email to " + recipient + " - " + subject + "\n" + body)
  }

}
