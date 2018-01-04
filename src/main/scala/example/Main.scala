package example

import example.clients.Client

object Main extends App {
  val client = new Client("localhost", 2020)

  client.start()
}
