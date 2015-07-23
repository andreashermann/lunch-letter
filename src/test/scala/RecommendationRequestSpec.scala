import com.google.gson.{JsonArray, JsonObject, JsonParser}
import org.scalamock.scalatest.MockFactory
import org.scalatest.{Entry, FlatSpec, Matchers}

class RecommendationRequestSpec extends FlatSpec with MockFactory with Matchers {

  it should "parse the recommendation response" in {
    val jsonString = "{\"itemScores\":[{\"item\":\"52\",\"score\":9.582509402541834},{\"item\":\"95\",\"score\":8.017236650368387}]}"
    val engineResponse = new JsonParser().parse(jsonString).asInstanceOf[JsonObject]
    val engine = mock[RecommendationEngine]
    (engine.getRecommendations _).expects(*).once().returning(engineResponse)

    val recommendations = new RecommendationRequest(engine).getRecommendationIds("userId")

    recommendations should contain theSameElementsAs List("52", "95")
  }

  it should "parse the user list" in {
    val jsonString = "[{\"entityId\":\"userA\"}, {\"entityId\":\"userB\"}]"
    val engineResponse = new JsonParser().parse(jsonString).asInstanceOf[JsonArray]
    val engine = mock[RecommendationEngine]
    (engine.getUsers _).expects().once().returning(engineResponse)

    val users = new RecommendationRequest(engine).getUserIds()

    users should contain theSameElementsAs List("userA", "userB")
  }

  it should "parse the restaurant list" in {
    val jsonString = "[{\"entityId\":\"1656\",\"properties\":{\"name\":\"The restaurant\"}}]";
    val engineResponse = new JsonParser().parse(jsonString).asInstanceOf[JsonArray]
    val engine = mock[RecommendationEngine]
    (engine.getRestaurants _).expects().once().returning(engineResponse)

    val restaurants = new RecommendationRequest(engine).getRestaurantsById();

    restaurants should contain key "1656"
    restaurants.get("1656").get("name") should be ("The restaurant")
  }
}
