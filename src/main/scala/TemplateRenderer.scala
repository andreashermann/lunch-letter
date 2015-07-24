import java.net.URLEncoder

import org.fusesource.scalate.TemplateEngine

import scala.util.Random

class TemplateRenderer {

  val engine = new TemplateEngine

  def render(userId: String, restaurants: List[Any]) = {
    val salutation = Random.shuffle(List("G'day!", "Guten Tag!", "Bonjour!", "Buenos Dias!", "Buon Giorno!", "Goedendag!", "Konnichiwa!", "An-nyeong-ha-se-yo!")).head
    val images = Random.shuffle(List(
      "https://farm8.staticflickr.com/7535/15638549207_bb59152266_n.jpg",
      "https://farm8.staticflickr.com/7476/15821829991_68ac4aa8e6_n.jpg",
      "https://farm8.staticflickr.com/7553/15821827311_201e5419dd_n.jpg",
      "https://farm4.staticflickr.com/3667/9356160984_fe56ab0015_n.jpg",
      "https://farm3.staticflickr.com/2877/9006446155_9eca1163ae_n.jpg"
    )).take(restaurants.size)

    restaurants
      .zip(images)
      .foreach({ case (restaurant : Any, imageLink : String) => restaurant.asInstanceOf[scala.collection.mutable.Map[String,String]].put("imageLink", imageLink)} )


    val attributes = Map(
      "restaurants" -> restaurants,
      "salutation" -> salutation,
      "userId" ->  URLEncoder.encode(userId, "UTF-8"))
    engine.layout("/template.mustache", attributes)
  }
}
