package lectures.part3FunctionalProgramming

import scala.util.{Failure, Random, Success, Try}

object HandlingFailures extends App {

  // create success and failures
  val asuccess = Success(2)
  val aFailure = Failure(new RuntimeException("Super Failure"))
  println(aFailure)
  println(asuccess)

  def unsafeMethod():String = throw new RuntimeException("No String")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // Syntax sugar
  val anotherPotentialFailure = Try{
    1/0
  }

  println("Another Failure::"+anotherPotentialFailure)

  // Utilities
  println(anotherPotentialFailure.isSuccess)

  //orElse
  def backup():String = "A VALID result"
  val failbackTry = Try(unsafeMethod()).orElse(Try(backup()))
  println("FailBackupTry"+failbackTry)

  def betterUnsafeMethod ():Try[String] = Failure(new RuntimeException)
  def betterBackupMethod() : Try[String] = Success("Better backup")
  val betterFailBack = betterUnsafeMethod() orElse betterBackupMethod()

  println("Better failback::"+betterFailBack)

  // Map,flatmap,filter
  println(asuccess.map(_*2))
  println(asuccess.flatMap(x=>Success(x*10)))
  println(asuccess.filter(_>10))


  val hostName = "localhost"
  val port = "8080"
  def renderHtml(page:String) = println(page)
  class Connection{
    def get(url:String):String = {
      val random = new Random(System.nanoTime())
      if( random.nextBoolean()) "<html> ... </html?"
      else throw new RuntimeException("Conenction Intereptud")
    }

    def getSafe(url:String):Try[String] = Try(get(url))
  }
  object HttpService{
    val random = new Random(System.nanoTime())
    def getConnection(host:String,port:String):Connection = {
      if( random.nextBoolean()) new Connection
      else throw new RuntimeException("Some one else took port")
    }
    def getsafeConnection(host:String,port: String):Try[Connection] = Try(getConnection(host,port))
  }
val possibleConnection =HttpService.getsafeConnection(hostName,port)
  val possibleHTML = possibleConnection.flatMap(conection=> conection.getSafe("/home"))
  possibleHTML.foreach(renderHtml)

  // shortHanded version
  HttpService.getsafeConnection(hostName,port).flatMap(connection=>connection.getSafe("/home")).foreach(renderHtml)

  // for-comprehensive
  for {
    connection <- HttpService.getsafeConnection(hostName,port)
    html <- connection.getSafe("/home")

  } renderHtml(html)


}
