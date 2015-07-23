import com.google.gson.JsonElement
import main.scala.SmtpMailer
import scala.collection.mutable.Map

object Main {
  def main(args: Array[String]) = {
    val recommendationRequest = new RecommendationRequest(new RecommendationEngine)
    val restaurantsById = recommendationRequest.getRestaurantsById();
    val users = recommendationRequest.getUserIds()
    val mailer = new SmtpMailer
    users.foreach(x => processUser(x, restaurantsById, recommendationRequest, mailer))
  }

  def processUser(emailAddress: String, restaurantsById: Map[String, Map[String, JsonElement]],
                  recommendationRequest: RecommendationRequest, mailer: SmtpMailer) = {
    val recommendationIds = recommendationRequest.getRecommendationIds(emailAddress)
    val restraurants = recommendationIds.map(id => restaurantsById.get(id))

    val body = "Giusi's"
    mailer.send(emailAddress, "LunchLetter Recommendations Do 23.7.", body)
    //sendMail(user, "Giusi's")
  }

  def sendMail(user: String, recommendation: String) = {
    println("Recommendations for " + user + ": " + recommendation)
  }
}
