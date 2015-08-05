import java.net.URLEncoder
import java.util.Base64
import java.nio.charset.StandardCharsets

import org.fusesource.scalate.TemplateEngine

import scala.util.Random

class TemplateRenderer {

  val engine = new TemplateEngine
  val KEEN_PROJECT_ID = "55c249e459949a551c0d4bac"
  val KEEN_WRITE_KEY = System.getenv("KEEN_WRITE_KEY")

  def render(userId: String, restaurants: List[Any]) : String = {
    val salutation = Random.shuffle(List("G'day!", "Guten Tag!", "Bonjour!", "Buenos Dias!", "Buon Giorno!", "Goedendag!", "Konnichiwa!", "An-nyeong-ha-se-yo!")).head
	
	val eventProperties: String = "{\"userId\":\"" + userId + "\"}"
	val base64EventProperties: String = Base64.getEncoder.encodeToString(eventProperties.getBytes(StandardCharsets.UTF_8))

    val attributes:Map[String,Any] = Map(
      "restaurants" -> restaurants,
      "salutation" -> salutation,
      "userId" -> URLEncoder.encode(userId, "UTF-8"),
      "projectId" -> KEEN_PROJECT_ID,
      "writeKey" -> KEEN_WRITE_KEY,
      "eventData" -> URLEncoder.encode(base64EventProperties))
    engine.layout("/template.mustache", attributes)
  }
}
