package lectures.part2OOP

object CaseClasses extends App {

  case class Person(name:String, age:Int)
  /*
  * 1. Class parameters converted to fields
  * */
  val jim = new Person("Jim",2)
  println(jim.name)
  // 2. Sensiable toString method
  // println(instance) = println(instance.toString)
  println(jim.toString)
  println(jim)

  // 3. equals and hashCode implemented out of box
  val jim2 = new Person("Jim",2)

  println(jim == jim2)

  //4. Case classes have copy method
  val jim3 = jim.copy(age=45)
  println(jim3)

  //5 . Case classes have companion objects
  val person = Person
  val mary = Person("mary",23)

  // 6 . Case classes are serializable -> use full in akka

  // 7 . Case classes are extractor patterns => CCs can be used in PATTERN MATCHING

  case object UnitedKingdom{
    def name:String = "The UK of GB nd NI"
  }
}
