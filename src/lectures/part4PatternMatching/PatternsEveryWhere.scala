package lectures.part4PatternMatching

object PatternsEveryWhere extends App {

  // big idea #1
  try{

  }catch {
    case e: RuntimeException => "Runtime"
    case npe: NullPointerException => "Npe"
    case _=> "some other"
  }

  // cathes are actually matches

  //Big idea 2
  val list = List(1,2,3,4)
  val evenOnes = for {
    x <- list if x%2 ==0
  } yield x*10

  // Generator also based on PATTERN MATCHING

  val tupple = List((1,2),(3,4))
  val filterTuple = for {
    (first,second) <- tupple
  } yield first*second

  // case classes , :: operators

  // big idea #3
  val tuple = (1,2,3)
  val (a,b,c) = tuple
  println(b)

  val head:: tail = list
  println(head)
  println(tail)

  // big idea #4
  // partial function
  val mappedList = list.map{
    case v if v%2==0 => v+" is even"
    case 1 => "the one"
    case _=> "something"
  }
  println(mappedList)
}
