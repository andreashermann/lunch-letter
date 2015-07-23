import com.google.gson.{JsonArray, JsonObject, JsonParser}

import scala.collection.Map
import scala.io.Source._

class RecommendationEngineStub extends RecommendationEngine {

  override def getUsers() : JsonArray = {
    val jsonString = fromInputStream(getClass.getResourceAsStream("users.json")).mkString
    new JsonParser().parse(jsonString).asInstanceOf[JsonArray]
  }

  override def getRecommendations(arguments: Map[String, AnyRef]): JsonObject = {
    val jsonString = fromInputStream(getClass.getResourceAsStream("recommendations.json")).mkString
    new JsonParser().parse(jsonString).asInstanceOf[JsonObject]
  }

  override def getRestaurants() : JsonArray = {
    val jsonString = fromInputStream(getClass.getResourceAsStream("restaurants.json")).mkString
    new JsonParser().parse(jsonString).asInstanceOf[JsonArray]
  }
}
