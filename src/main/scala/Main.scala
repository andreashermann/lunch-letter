import com.google.gson.JsonElement
import java.time.LocalDate

import main.scala.SmtpMailer
import scala.collection.mutable.Map

object Main {

  val engine = new RecommendationEngineStub() // new RecommendationEngine
  val mailer = new StubMailer // new SmtpMailer

  def main(args: Array[String]) = {
    val recommendationRequest = new RecommendationRequest(engine)
    val restaurantsById = recommendationRequest.getRestaurantsById();
    val users = recommendationRequest.getUserIds()
    users.foreach(x => processUser(x, restaurantsById, recommendationRequest, mailer))
  }

  def processUser(emailAddress: String, restaurantsById: Map[String, Map[String, String]],
                  recommendationRequest: RecommendationRequest, mailer: SmtpMailer) = {
    val recommendationIds = recommendationRequest.getRecommendationIds(emailAddress)
    val restaurants : List[Map[String,String]] = recommendationIds.map(id => restaurantsById.get(id)).flatten
    print(restaurants)

    val date = LocalDate.now().toString
    val body = new TemplateRenderer().render(restaurants)
    mailer.send(emailAddress, "LunchLetter Recommendations " + date, body)
  }
}
