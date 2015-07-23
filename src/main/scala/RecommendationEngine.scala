import com.google.gson.JsonObject
import io.prediction.EngineClient

import scala.collection.Map

class RecommendationEngine {
  def sendQuery(arguments: Map[String, AnyRef]): JsonObject = {
    val engine = new EngineClient("http://engine:80")
    engine.sendQuery(arguments.asInstanceOf[java.util.Map[String, AnyRef]])
  }
}
