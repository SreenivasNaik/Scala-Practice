package lectures.part2OOP

object Objects extends App {

  //SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY - STATIC IS NOT PRESENT
  object Person{  // Type + its only Instance
    val N_EYES = 2;
    def canFly:Boolean = false
  }

  println(Person.N_EYES)
  println(Person.canFly)

  // Scala objects are Singletons

  val mary = Person
  val ja = Person
  println(mary == ja)

}
