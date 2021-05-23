package lectures.part2OOP

object AnonymousClasses extends App {

  abstract class Animal{
    def eat:Unit
  }

  // Anonymous class
  val funnyAnimal:Animal = new Animal {
    override def eat: Unit = println("Funny Animal")
  }
  /* Equivalent with
  * class AnonymousClasses$$anon$1 extends Animal{
    override def eat: Unit = println("Funny Animal")
  }
  * val funnyAnimal :Animal = new AnonymousClasses$$anon$1
  * */

  println(funnyAnimal.getClass)

  class Person(val name:String){
    def sayHai:Unit = println(s"Hi My name is $name")
  }
  val jim = new Person("Jim"){
    override def sayHai:Unit = println(s"Hi My name is ${this.name} how can I help")
  }
  jim.sayHai
}
