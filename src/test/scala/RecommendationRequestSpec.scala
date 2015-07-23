import com.google.gson.{JsonParser, JsonObject}
import org.scalatest.{Matchers, FlatSpec}
import org.scalamock.scalatest.MockFactory

class RecommendationRequestSpec extends FlatSpec with MockFactory with Matchers {

  it should "parse the response" in {
    val jsonString = "{\"itemScores\":[{\"item\":\"52\",\"score\":9.582509402541834},{\"item\":\"95\",\"score\":8.017236650368387}]}"
    val engineResponse = new JsonParser().parse(jsonString).asInstanceOf[JsonObject]

    val engine = mock[RecommendationEngine]
    (engine.sendQuery _).expects(*).once().returning(engineResponse)

    new RecommendationRequest(engine).getRecommendations("userId") should contain theSameElementsAs List("52", "95")
  }
}
