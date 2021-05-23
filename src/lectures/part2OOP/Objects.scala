package lectures.part2OOP

object Objects extends App {

  //SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY - STATIC IS NOT PRESENT
  object Person{  // Type + its only Instance
    // Static or Class level functionality
    val N_EYES = 2;
    def canFly:Boolean = false
    //factory method
  //  def from(mother:Person,father:Person):Person = new Person("Baby")
    def apply(mother:Person,father:Person):Person = new Person("Baby")
  }

  class Person(val name:String){
    // Instance level functionality

  }
  // COMPANIONS
  println(Person.N_EYES) // Class Level Settings
  println(Person.canFly)

  // Scala objects are Singletons


  val mary = new Person("Marry")
  val ja = new Person("Jhon")
  println(mary == ja)

  //val baby = Person.from(mary,ja)
  val baby = Person(mary,ja)
}
