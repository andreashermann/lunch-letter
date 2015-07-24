import com.google.gson.{JsonArray, JsonParser, JsonObject}
import io.prediction.EngineClient

import scala.collection.Map
import scala.io.Source

class RecommendationEngine {

  val host = "www.lunchletter.ch:7070"

  val ENGINE_ACCESS_KEY = System.getenv("ENGINE_ACCESS_KEY")

  def getRecommendations(arguments: Map[String, AnyRef]): JsonObject = {
    val engine = new EngineClient("http://" + host)
    engine.sendQuery(arguments.asInstanceOf[java.util.Map[String, AnyRef]])
  }

  def getUsers() : JsonArray = {
    val url = "http://" + host + "/events.json?event=add_user&accessKey=" + ENGINE_ACCESS_KEY
    new JsonParser().parse(Source.fromURL(url).mkString).asInstanceOf[JsonArray]
  }

  def getRestaurants() : JsonArray = {
    val url = "http://" + host + "/events.json?event=add_restaurant&accessKey=" + ENGINE_ACCESS_KEY
    new JsonParser().parse(Source.fromURL(url).mkString).asInstanceOf[JsonArray]
  }
}
