package lectures.part3FunctionalProgramming

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3,4)
  println(list)

  //map
  println(list.map(_+1))
  println(list.map(_+"is a number"))
  // filter
  println(list.filter(_%2==0))
  val toPair = (x:Int)=>List(x,x+1)
  println(list.flatMap(toPair))

  val chars = List('a','b','c','d')
  val colors = List("Red","Blue")
  // print all possible combinations
  val combinations = list.flatMap(n=>chars.map(c=>""+c+n))
  println(combinations)
// ITERATIONS
  println(list.flatMap(n=>chars.flatMap(c=>colors.map(colors=>" "+c+n+colors))))

  // foreach
  list.foreach(println)

  // for-comprehensions

  val forComprehensions = for {
    n <- list if( n%2 ==0)
    c <- chars
    color <- colors
  } yield " "+c+n+color

  println(forComprehensions)

  for {
    n <- list
  } println(n)

  // Syntax overload
  println(list.map{
    x=>x*2
  })

  /*
  * A small collection of at most ONE element - MayBe[+T]
  *  - map,flatmap,filter
  * */

}
