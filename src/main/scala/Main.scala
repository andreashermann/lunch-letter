import io.prediction.EngineClient
import main.scala.SmtpMailer

object Main {
    def main(args: Array[String]) = {
        val users : List[String] = List("a.v.hermann@gmail.com", "baestu.kohler@gmail.com")

        users.foreach(processUser)
    }

    def processUser(user : String) = {
        val userId = "u1"
        val recommendationIds = new RecommendationRequest(new EngineClient("http://engine:80"))
            .getRecommendations(userId)
        val mailer = new SmtpMailer
        val body = "Giusi's"
        mailer.send(user, "LunchLetter Recommendations Do 23.7.", body)
        //sendMail(user, "Giusi's")
    }

    def sendMail(user: String, recommendation : String) = {
        println("Recommendations for " + user + ": " + recommendation)
    }
}
