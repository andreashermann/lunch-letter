import com.google.gson.{JsonElement, JsonObject}

import scala.collection.JavaConversions._
import scala.collection.mutable._

class RecommendationRequest(engineClient: RecommendationEngine) {

  def getRecommendationIds(userId: String): List[String] = {
    val arguments = Map("userEntityId" -> userId.asInstanceOf[AnyRef], "number" -> 3.asInstanceOf[AnyRef])
    val response = engineClient.getRecommendations(arguments)

    response.getAsJsonArray("itemScores")
      .map(_.getAsJsonObject().get("item").getAsString)
      .toList
  }

  def getUserIds(): List[String] = {
    engineClient.getUsers()
      .map(_.getAsJsonObject().get("entityId").getAsString)
      .toList
  }

  def getRestaurantsById(): Map[String, Map[String, String]] = {
    val restaurants = engineClient.getRestaurants().map(_.getAsJsonObject).toList

    val restaurantsById : Map[String, Map[String, String]] = Map()

    for (restaurant <- restaurants) {
      val properties : Map[String, String] = Map()
      for(entry <- restaurant.get("properties").getAsJsonObject().entrySet()) {
        properties.put(entry.getKey, entry.getValue().getAsString)
      }
      properties.put("entityId", restaurant.get("entityId").getAsString)
      restaurantsById.put(restaurant.get("entityId").getAsString, properties)
    }
    restaurantsById
  }
}
