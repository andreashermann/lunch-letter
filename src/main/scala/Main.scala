import main.scala.SmtpMailer
import scala.collection.JavaConversions._

object Main {
  def main(args: Array[String]) = {
    val recommendationRequest = new RecommendationRequest(new RecommendationEngine)
    val users = recommendationRequest.getUserIds()
    val mailer = new SmtpMailer
    users.foreach(x => processUser(x, recommendationRequest, mailer))
  }

  def processUser(emailAddress: String, recommendationRequest : RecommendationRequest, mailer : SmtpMailer) = {
    val recommendationIds = recommendationRequest.getRecommendationIds(emailAddress)
    val body = "Giusi's"
    mailer.send(emailAddress, "LunchLetter Recommendations Do 23.7.", body)
    //sendMail(user, "Giusi's")
  }

  def sendMail(user: String, recommendation: String) = {
    println("Recommendations for " + user + ": " + recommendation)
  }
}
