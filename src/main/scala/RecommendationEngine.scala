import com.google.gson.{JsonArray, JsonParser, JsonObject}
import io.prediction.EngineClient
import scala.collection.Map
import scala.io.Source

class RecommendationEngine {

  val eventHost = "www.lunchletter.ch:7070"
  var queryHost = "www.lunchletter.ch:8000"

  val ENGINE_ACCESS_KEY = System.getenv("ENGINE_ACCESS_KEY")

  def getRecommendations(arguments: Map[String, AnyRef]): JsonObject = {
    print(arguments)
    val engine = new EngineClient("http://" + queryHost)

    val javaMap = new java.util.HashMap[String, AnyRef]()
    arguments.foreach((e: (String, AnyRef)) => javaMap.put(e._1, e._2))

    engine.sendQuery(javaMap)
  }

  def getUsers() : JsonArray = {
    val url = "http://" + eventHost + "/events.json?event=add_user&accessKey=" + ENGINE_ACCESS_KEY
    new JsonParser().parse(Source.fromURL(url).mkString).asInstanceOf[JsonArray]
  }

  def getRestaurants() : JsonArray = {
    val url = "http://" + eventHost + "/events.json?event=add_restaurant&accessKey=" + ENGINE_ACCESS_KEY
    new JsonParser().parse(Source.fromURL(url).mkString).asInstanceOf[JsonArray]
  }
}
