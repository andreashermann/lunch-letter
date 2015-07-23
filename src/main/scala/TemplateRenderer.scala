import org.fusesource.scalate.TemplateEngine

import scala.util.Random

class TemplateRenderer {

  val engine = new TemplateEngine

  def render(restaurants: List[Any]) = {
    val salutation = Random.shuffle(List("G'day!","Guten Tag!","Bonjour!","Buenos Dias!","Buon Giorno!","Goedendag!","Konnichiwa!","An-nyeong-ha-se-yo!")).head
    val attributes = Map("restaurants" -> restaurants, "salutation" -> salutation) //.asInstanceOf[Map[String,Any]]
    engine.layout("/template.mustache", attributes)
  }

}
