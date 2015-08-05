import com.google.gson.JsonObject
import java.net.URLEncoder

import scala.collection.JavaConversions._
import scala.collection.immutable
import scala.collection.mutable._
import scala.util.Random

class RecommendationRequest(engineClient: RecommendationEngine) {

  def getRecommendationIds(userId: String): List[String] = {
    val arguments = Map("user" -> userId.asInstanceOf[AnyRef], "num" -> 3.asInstanceOf[AnyRef])
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

  def getRestaurantsById(): immutable.Map[String, immutable.Map[String, String]] = {
    val images = Stream.continually(Random.shuffle(List(
      "https://farm8.staticflickr.com/7535/15638549207_bb59152266_n.jpg",
      "https://farm8.staticflickr.com/7476/15821829991_68ac4aa8e6_n.jpg",
      "https://farm8.staticflickr.com/7553/15821827311_201e5419dd_n.jpg",
      "https://farm4.staticflickr.com/3667/9356160984_fe56ab0015_n.jpg",
      "https://farm3.staticflickr.com/2877/9006446155_9eca1163ae_n.jpg",
      "https://farm3.staticflickr.com/2101/2529856456_368530c5ab_n.jpg",
      "https://farm1.staticflickr.com/37/80200323_2efeb7601c.jpg",
      "https://farm6.staticflickr.com/5484/14330259870_eb11fe531d_n.jpg"
    ))).flatten

    engineClient.getRestaurants().map(_.getAsJsonObject)
      .zip(images)
      .map({ case (restaurant: JsonObject, imageLink: String) => restaurant.get("entityId").getAsString -> {
        val properties = restaurant.get("properties").getAsJsonObject();
        properties.addProperty("entityId", restaurant.get("entityId").getAsString)
        properties.addProperty("imageLink", imageLink);
        val result = restaurant.get("properties").getAsJsonObject().entrySet()
          .map(entry => entry.getKey -> entry.getValue().getAsString)
          .toMap
        
        // URLencode some properties here since the templating engine is not capable of this
        result + ("_name_" -> URLEncoder.encode(result.get("name") getOrElse "","US-ASCII"), 
        	"_address1_" -> URLEncoder.encode(result.get("address1") getOrElse "","US-ASCII"), 
        	"_address2_" -> URLEncoder.encode(result.get("address2") getOrElse "","US-ASCII")
        )
        }
      })
      .toMap
  }
}
