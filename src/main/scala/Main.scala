import java.time.LocalDate

import main.scala.SmtpMailer

import scala.collection.immutable.Map

object Main {

  val engine = new RecommendationEngine() // new RecommendationEngine
  val mailer = new StubMailer // new SmtpMailer

  def main(args: Array[String]) : Unit = {
    val recommendationRequest = new RecommendationRequest(engine)
    val restaurantsById = recommendationRequest.getRestaurantsById();
    val users = recommendationRequest.getUserIds()
    users.foreach(x => processUser(x, restaurantsById, recommendationRequest, mailer))
    sys.exit(0)
  }

  def processUser(emailAddress: String, restaurantsById: Map[String, Map[String, String]],
                  recommendationRequest: RecommendationRequest, mailer: SmtpMailer) = {
    val recommendationIds = recommendationRequest.getRecommendationIds(emailAddress)
    val restaurants: List[Map[String, String]] = recommendationIds.map(id => restaurantsById.get(id)).flatten
    //print(restaurants)

    val date = LocalDate.now().toString
    val body = new TemplateRenderer().render(emailAddress, restaurants)
    mailer.send(emailAddress, "LunchLetter Recommendations " + date, body)
    
    // TODO: save last sent date
  }
}
