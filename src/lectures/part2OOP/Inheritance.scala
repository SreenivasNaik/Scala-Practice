package lectures.part2OOP

object Inheritance extends App {

  // Super Class or Parent class
  class Animal{
    val creatureType = "wild"
    def eat = println("Nonom")
   //private def eat = println("Nonom")
  }
  // Sub class or Child class
  // Scala allows only single class inheritance only
  class Cat extends Animal{
    def crunch ={
      eat
      println("Crunch :: Crunch")
    }
  }
  val cat = new Cat
  cat.crunch

  // Constructors
  class  Person(name:String,age:Int){
    def this(name:String) = this(name,0)
  }
  class Adult(name:String,age:Int,idCard:String) extends Person(name ,age )
  class Adult1(name:String,age:Int,idCard:String) extends Person(name)

  // OverRididng
  class dog extends Animal{
    override val creatureType: String = "Domestic" // Val overriding
    override  def eat = println("Dog Eats ") // Method Overriding
  }
  val dogInstance = new dog
  dogInstance.eat
  dogInstance.creatureType

  class cats(override val creatureType: String) extends Animal{
    override def eat = {
      super.eat
      println("Cat eats")
    }
  }
  val catInstace = new cats("Soft")
  catInstace.creatureType
  catInstace.eat

  val unknownAnimal:Animal = new cats("soft")
  unknownAnimal.creatureType
  unknownAnimal.eat

/* Preventing Extending or Overriding
* 1 .  Use final Keyword on members so that we can't override the members
* 2. Use final on class so that it is not avaible for extending
* 3. use sealed on the class-> it can extends class in this file and prevent extension in other files
*
* */

}
