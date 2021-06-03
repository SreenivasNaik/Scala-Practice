package lectures.part4PatternMatching

import scala.util.Random

object PatternMatching extends App {

  val random = new Random
  val x = random.nextInt(5)

  val description = x match {
    case 1 => " The one"
    case 2 => "Double "
    case 3 => "Tripple"
    case _ => "Some Thing else"
  }

  println(x+" - "+description)

  // 1. Decompose values

  case class Person(name:String,age: Int)
  val bob = Person("Bob",20)
  val greeting = bob match {
    case Person(name, age) if(age <21 ) => s"Hi I am $name and my age is $age i am not eligible"
    case Person(name, age) => s"Hi I am $name and my age is $age"
    case _ => "I don;t know"
  }
  println(greeting)
 /* 1. Cases are matched in order
 * 2. What if no cases match ? => we will get Match Error exception
 * 3. Type of the PM expression => unified type of all the types in all the cases
 * 4. PM works really well with case classes
 * */

  //2. Pattern Matching on sealed hierarchies
  sealed class Animal
  case class Dog(breed:String) extends Animal
  case class Parrot(greeting:String) extends Animal

  val animal:Animal = Dog("Terra")
  animal match {
    case Dog(someBreed) => println(s"Matches a dog of the $someBreed breed")
  }

  // Match EveryThing
  val isEven= x match {
    case n if n%2==0 => true
    case _=> false
  }
  println("isEven:"+isEven)

  trait Expr
  case class Number(n:Int) extends Expr
  case class Sum(e1:Expr , e2:Expr) extends Expr
  case class Prod(e1:Expr , e2:Expr) extends Expr
  /*
  *  exercise -> simple function uses PM
  *  takes an EXPr -> human readable format
  *   Sum(Num(2),Num(3)) = > 2+3
  *   Sum(Num(2),Num(1),NUm(3)) = >1+ 2+3
  *   Prod(Sum(Num(2),Num(1)),NUm(3)) = >(1+ 2)*3
  *   Sum(Prod(Num(2),Num(1)),NUm(3)) = >(1* 2)+3
  * */

  def show(e:Expr):String = e match {
    case Number(n) => s"$n"
    case Sum(e1,e2) => show(e1)+ " + "+show(e2)
    case Prod(e1,e2) => {
      def mayBeSHowParams(exp:Expr)= exp match {
        case Prod(_,_) => show(exp)
        case Number(_) => show(exp)
        case _=> "("+show(exp)+")"
      }
      mayBeSHowParams(e1)+" * "+mayBeSHowParams(e2)
    }


  }
  println(show(Sum(Number(2),Number(3)) ))
  println(show(Sum(Sum(Number(1),Number(2)),Number(3)) ))
  println(show(Prod(Sum(Number(2),Number(1)),Number (3))))
  println(show(Sum(Prod(Number(2),Number(1)),Number(3))))
}
