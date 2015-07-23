object Main {
    def main(args: Array[String]) = {
        val users : List[String] = List("a.v.hermann@gmail.com", "baestu.kohler@gmail.com")

        users.foreach(processUser)
    }

    def processUser(user : String) = {
        sendMail(user, "Giusi's")
    }

    def sendMail(user: String, recommendation : String) = {
        println("Recommendations for " + user + ": " + recommendation)
    }
}
