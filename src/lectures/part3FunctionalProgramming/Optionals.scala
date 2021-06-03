package lectures.part3FunctionalProgramming

import scala.util.Random

object Optionals extends App {

  val myFirstOption:Option[Int] = Some(3)
  val noOption :Option[Int] = None

  println("MY option: "+myFirstOption.get)

  def unsafeMethod():String = null
  // val result = Some(unsafeMethod()) ==> Wrong
  val result = Option(unsafeMethod()) // Some or None
  println(result)

  // chained method
  def backedMethod():String = "A valid result"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backedMethod()))
  println(chainedResult)

  // Design unsafe API
  def betterUnsafeMethod():Option[String]= None
  def betterbackupMethod():Option[String]= Some("A valid Result")
  val betterChainedResult = betterUnsafeMethod() orElse betterbackupMethod()
  println(betterChainedResult)

  // Functions
  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - > DON"T use

  println(myFirstOption.map(_*3))
  println(myFirstOption.filter(x=>x>10))
  println(myFirstOption.flatMap(x=>Option(x*10)))

  // For - comprehensions
  val config :Map[String,String] = Map(
    "Host"-> "133.232.322.3",
    "Port"-> "9090"
  )
  class Connection {
    def connect = "Connected"
  }
  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (true) Some(new Connection)
      else None
  }
    val host = config.get("Host")
    val port = config.get("Port")

    /*
    *   if(host!=null){
    *     if(port!=null){
    *       return Connection.apply(host,port)
    * }
    * }
    *
    * */
    val connection = host.flatMap(h=>port.flatMap(p=>Connection.apply(h,p)))
    /*
    *   if(connection !=null)
    *     return connection.connect
    *   return null
    *
    * */
    val connectionStatus = connection.map(c=>c.connect)

  // if(connectionStatus!=null) print(Some(connectionStatus.get) else println(None)
    println("connectionStatus"+connectionStatus)
    connectionStatus.foreach(println)


    // Chained calls
    config.get("Host").
      flatMap(host=>config.get("Port").
      flatMap(port=>Connection(host,port)).
      map(conection=>conection.connect)).foreach(println)

    // for comprehensions
  val ForconnectionStatus = for {
    host <- config.get("Host")
    port <- config.get("Port")
    connection <- Connection.apply(host,port)
  } yield connection.connect

  ForconnectionStatus.foreach(println)
}