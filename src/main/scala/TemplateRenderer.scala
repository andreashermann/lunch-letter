import org.fusesource.scalate.TemplateEngine

class TemplateRenderer {

  val engine = new TemplateEngine

  def render(restaurants: List[Any]) = {
    val attributes = Map("restaurants" -> restaurants).asInstanceOf[Map[String,Any]]
    engine.layout("/template.mustache", attributes)
  }

}
