import main.scala.SmtpMailer
import scala.collection.JavaConversions._

object Main {
  def main(args: Array[String]) = {
    val recommendationEngine = new RecommendationEngine
    val users = recommendationEngine.getUsers()

    users.foreach(x => processUser(x.getAsJsonObject().get("entityId").getAsString))
  }

  def processUser(emailAddress: String) = {
    val recommendationIds = new RecommendationRequest(new RecommendationEngine)
      .getRecommendationIds(emailAddress)
    val mailer = new SmtpMailer
    val body = "Giusi's"
    mailer.send(emailAddress, "LunchLetter Recommendations Do 23.7.", body)
    //sendMail(user, "Giusi's")
  }

  def sendMail(user: String, recommendation: String) = {
    println("Recommendations for " + user + ": " + recommendation)
  }
}
