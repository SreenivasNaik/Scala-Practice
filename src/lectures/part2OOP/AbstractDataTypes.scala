package lectures.part2OOP

object AbstractDataTypes  extends  App {

  // abstract
  abstract class Animal{
    val creatureType:String
    def eat:Unit

  }

  class Dog extends Animal{
    override val creatureType: String = "Dog"

     def eat: Unit = println("Dog Eats")
  }

  /* Traits Vs Abstract class
  * 1. Traits don;t have constrcuture parameters
  * 2. Multiple traits may be inheritance by same class
  * 3. traits are behavior where as abstract classes are things
  * */
  trait carnivore{
    def eat(animal: Animal):Unit
  }
  trait coldBlooded

  class Crocodile extends Animal with carnivore with coldBlooded {
    override val creatureType: String = "Crocodile"
    def eat:Unit = "Crocodile eats"

    override def eat(animal: Animal): Unit = println(s"I am $creatureType and eating ${animal.creatureType}" )
  }

  val dog = new Dog
  val crod = new Crocodile
  crod.eat(dog)


}
