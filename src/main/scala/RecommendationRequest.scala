import io.prediction.EngineClient

import scala.collection.mutable._
import collection.JavaConversions._

class RecommendationRequest(engineClient: EngineClient) {

  def getRecommendations(userId: String) : List[String] = {
    val arguments : java.util.Map[String,AnyRef] = HashMap[String,AnyRef]("userEntityId" -> userId, "number" -> 3)
    val response = engineClient.sendQuery(arguments)

    response.getAsJsonArray("itemScores")
      .map(_.getAsJsonObject().get("itemEntityId").getAsString)
      .toList
  }
}
