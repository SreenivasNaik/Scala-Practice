package lectures.part3FunctionalProgramming

object WhatIsFunction extends App {

  // Dream : Use Functions as first class Elements
  // but Problem : OOP
  val doubler = new MyFunction[Int,Int]{
    override def apply(element:Int):Int = element*2
  }
  println(doubler(2))

  val stringToIntConverter = new Function[String,Int] {
    override def apply(v1: String): Int = v1.toInt
  }

  println(stringToIntConverter("2")+3)

  val adder:((Int,Int)=>Int) = new Function2[Int,Int,Int] {
    override def apply(v1: Int, v2: Int): Int = v1+v2
  }

  // Function types Function2[A,B,R] === (A,B)=>R

  // ALL SCALA FUNCTIONS ARE OBJECTS

  //1 . function which takes 2 strings and concate them

  val stringConcat:(String,String)=>String = new Function2[String,String,String]{
    override def apply(v1: String, v2: String): String = v1.concat(v2)
  }
  println(stringConcat("Sreenu","Naik"))

  /*
  * Define the function which takes an int and returns another function which takes int and return as int
  *
  * Func
  * */

  val superAdder : Function1[Int,Function1[Int,Int]] = new Function[Int,Function1[Int,Int]] {
    override def apply(x: Int): Function1[Int,Int] = new Function[Int,Int] {
      override def apply(y: Int): Int =x+y
    }
  }

  val adder3= superAdder(3) // Function1[Int,Int] returned to adder3
  println(adder3(4))

  println(superAdder(3)(4)) // curried Function
}

trait MyFunction[A,B] {
  def apply(element:A):B
}
