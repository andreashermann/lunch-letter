import org.fusesource.scalate.TemplateEngine

class TemplateRenderer {

  val engine = new TemplateEngine

  def render(recommendations: Map[String, Any]) = {
    engine.layout("/template.ssp", recommendations)
  }

}
