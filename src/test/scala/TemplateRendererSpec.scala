import com.google.gson.{JsonObject, JsonParser}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Matchers, FlatSpec}

class TemplateRendererSpec extends FlatSpec with MockFactory with Matchers {

  it should "render the template" in {

    val restaurants = List(Map("name" -> "Giusis"), Map("name" -> "Nooba"))
    val output = new TemplateRenderer().render(restaurants)

    output should be ("<ol><li>Giusis</li> <li>Nooba</li> </ol>")
  }
}