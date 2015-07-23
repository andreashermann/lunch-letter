import scala.collection.JavaConversions._
import scala.collection.mutable._

class RecommendationRequest(engineClient: RecommendationEngine) {

  def getRecommendations(userId: String): List[String] = {
    val arguments = Map("userEntityId" -> userId.asInstanceOf[AnyRef], "number" -> 3.asInstanceOf[AnyRef])
    val response = engineClient.sendQuery(arguments)

    response.getAsJsonArray("itemScores")
      .map(_.getAsJsonObject().get("item").getAsString)
      .toList
  }
}
