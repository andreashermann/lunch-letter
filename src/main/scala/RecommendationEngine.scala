import com.google.gson.{JsonArray, JsonParser, JsonObject}
import io.prediction.EngineClient

import scala.collection.Map
import scala.io.Source

class RecommendationEngine {

  //val host = "46.101.237.209:7070"
  val host = "engine:80"

  def getRecommendations(arguments: Map[String, AnyRef]): JsonObject = {
    val engine = new EngineClient("http://" + host)
    engine.sendQuery(arguments.asInstanceOf[java.util.Map[String, AnyRef]])
  }

  def getUsers() : JsonArray = {
    val url = "http://" + host + "/events.json?event=add_user&accessKey=swBRCzjYKNlmEHdAHBN6WEg9vVcxkt5NzbWc96w2E9xBElfWV1SIXhO6MPZA4CSf"
    new JsonParser().parse(Source.fromURL(url).mkString).asInstanceOf[JsonArray]
  }

  def getRestaurants() : JsonArray = {
    val url = "http://" + host + "/events.json?event=add_restaurant&accessKey=swBRCzjYKNlmEHdAHBN6WEg9vVcxkt5NzbWc96w2E9xBElfWV1SIXhO6MP"
    new JsonParser().parse(Source.fromURL(url).mkString).asInstanceOf[JsonArray]
  }
}
