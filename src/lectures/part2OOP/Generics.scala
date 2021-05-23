package lectures.part2OOP

object Generics  extends  App {

  class MyList[+A]{
    // use the type of A
    def add[B >:A](element:B):MyList[B] = ???
    /*
    * A = cat
    * B = Dog = Animal
    * */
  }
  class MyMap[key,value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]
  // Generic Methods
  object MyList{
    def empty[A]:MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int]

  // variance Problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  /* Q: if cat extends Animal does list of cats extends list of animals
  *
  * 1> Yes List[Cat] extends List[Animal] ==> COVARIANCE
  * */

  class CovariantList[+A]; // Covariance
  val animal:Animal = new Cat
  val animalList:CovariantList[Animal] = new CovariantList[Cat]
  // Can I add ANy Animal -> animalList.add(new Dog) ?? => We return List of Animals

  // 2. No:: Invariance
  class InvarianceList[A]
  // We can't substitute one with another one
  // val invarianceAnimalList:InvarianceList[Animal] = new InvarianceList[Cat]
   val invarianceAnimalList:InvarianceList[Animal] = new InvarianceList[Animal]

  // 3 .Hell No ->ContraVariance
  class Trainer[-A]
  val  trainer:Trainer[Cat] = new Trainer[Animal]


  // Bounded Types
  class Cage[A <: Animal](animal: A) // ==> Accepts only subtype of Animal

  val cage = new Cage(new Dog)

  class Car
  // val newCage = new Cage(new Car) => gives error as car is not subtype of animal

  class NewCage[A >: Animal](animal: A) // => Accepts only super type of Animal
}
