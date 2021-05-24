package lectures.part2OOP

//import playground.{Prince, Queen}
import playground._

import java.sql
import java.util.Date
import java.sql.{Date => SqlDate}
object PackagingAndImports extends App {

  // package members are accessiable by their simple name
  val writer = new Writer("Sreeni","Naik",1323)

    // package objects
  sayHello
  println(SPEED)
  // imports
  val prince = new Prince
  val queen = new Queen

  // 1 use Fully Qualified Names
  val date = new Date()
  val sqlDate = new sql.Date(2010,2,2)
  // use aliasing
  val sqlDateAlias = new SqlDate(1010,2,2)

}
