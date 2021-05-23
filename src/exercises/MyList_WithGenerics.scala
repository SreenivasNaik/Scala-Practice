package exercises

abstract class MyList1[+A] {
  /*
  *  head = first element of the list
  * tail = last element of the list
  * isEmpty -> list is empty or not
  *  add(int) -> new list with this element added
  *  toString => a string representation of the list
  * */

  def head:A
  def tail:MyList1[A]
  def isEmpty:Boolean
  def add[B>:A](element:B):MyList1[B]
   def printElements:String

  override def toString: String = "["+printElements+"]"
}
object Empty1 extends MyList1[Nothing]{
  def head:Nothing = throw new NoSuchElementException
  def tail:MyList1[Nothing] = throw new NoSuchElementException
  def isEmpty:Boolean = true
  def add[B >: Nothing](element:B):MyList1[B] = new Cons2(element,Empty1)

  override def printElements: String = ""
}

class Cons2[+A](h:A, t:MyList1[A]) extends MyList1[A] {
  def head: A = h

  def tail: MyList1[A] = t

  def isEmpty: Boolean = false

  def add[B>:A](element: B): MyList1[B] = new Cons2(element, this)

  override  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
}

object ListTest1 extends App {

  val listOfIntegers:MyList1[Int] = new Cons2(1,new Cons2(2,new Cons2(3,Empty1)))
  val listOfStrings:MyList1[String] = new Cons2("Hello",new Cons2[String]("Scala",Empty1))

 println(listOfIntegers.toString)
  println(listOfStrings.toString)


}

