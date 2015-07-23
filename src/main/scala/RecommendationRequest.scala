import io.prediction.EngineClient

import scala.collection.mutable._
import collection.JavaConversions._

class RecommendationRequest {

  val engineClient = new EngineClient("http://engine:80");

  def getRecommendations(userId: String) = {
    val arguments : java.util.Map[String,AnyRef] = HashMap[String,AnyRef]("userEntityId" -> userId, "number" -> 3)
    engineClient.sendQuery(arguments)
  }

}
