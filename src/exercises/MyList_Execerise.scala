package exercises
/*
* 1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
* 2. Generic trait MyTransformer[-A,B] with method transform(A) => B
* 3. MyList
*     -> map(tansformer) => MyList
*     -> filter(predicate) => MyList
*     -> flatMap(transformer) => MyList
*   class EvenPredicate extends MyPredicate[Int]
*   class StringToIntTransformer extends MyTransformer[String,Int]
*
* */


abstract class MyGenericList[+A] {
  /*
  *  head = first element of the list
  * tail = last element of the list
  * isEmpty -> list is empty or not
  *  add(int) -> new list with this element added
  *  toString => a string representation of the list
  * */

  def head:A
  def tail:MyGenericList[A]
  def isEmpty:Boolean
  def add[B>:A](element:B):MyGenericList[B]
   def printElements:String

  override def toString: String = "["+printElements+"]"

  def map[B](transformer: MyTransformer[A,B]):MyGenericList[B]
  def flatMap[B](transformer: MyTransformer[A,MyGenericList[B]]):MyGenericList[B]
  def filter(predicate: MyPredicate[A]):MyGenericList[A]
  def ++[B >: A](list:MyGenericList[B]):MyGenericList[B]
}
object EmptyGeneric extends MyGenericList[Nothing]{
  def head:Nothing = throw new NoSuchElementException
  def tail:MyGenericList[Nothing] = throw new NoSuchElementException
  def isEmpty:Boolean = true
  def add[B >: Nothing](element:B):MyGenericList[B] = new ConsGeneric(element,EmptyGeneric)

  override def printElements: String = ""

  def map[B](transformer: MyTransformer[Nothing,B]):MyGenericList[B] = EmptyGeneric
  def flatMap[B](transformer: MyTransformer[Nothing,MyGenericList[B]]):MyGenericList[B] = EmptyGeneric
  def filter(predicate: MyPredicate[Nothing]):MyGenericList[Nothing] = EmptyGeneric

  override def ++[B >: Nothing](list: MyGenericList[B]): MyGenericList[B] = list
}

class ConsGeneric[+A](h:A, t:MyGenericList[A]) extends MyGenericList[A] {
  def head: A = h

  def tail: MyGenericList[A] = t

  def isEmpty: Boolean = false

  def add[B>:A](element: B): MyGenericList[B] = new ConsGeneric(element, this)

  override  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def filter(predicate: MyPredicate[A]): MyGenericList[A] =
    if(predicate.test(h)) new ConsGeneric(h,t.filter(predicate))
    else t.filter(predicate)

  /*
  * [1,2,3].map(n*2)
  * */
  def map[B](transformer: MyTransformer[A,B]):MyGenericList[B] =
    new ConsGeneric[B](transformer.transform(h),t.map(transformer))

  def flatMap[B](transformer: MyTransformer[A,MyGenericList[B]]):MyGenericList[B] =
    transformer.transform(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyGenericList[B]): MyGenericList[B] = new ConsGeneric[B](h,t ++ list)
}

trait MyPredicate[-T]{
  def test(elem:T):Boolean
}
trait MyTransformer[-A,B]{
  def transform(elem:A):B
}

object ListTest2 extends App {

  val listOfIntegers:MyGenericList[Int] = new ConsGeneric(1,new ConsGeneric(2,new ConsGeneric(3,EmptyGeneric)))
  val listOfStrings:MyGenericList[String] = new ConsGeneric("Hello",new ConsGeneric[String]("Scala",EmptyGeneric))

 println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new MyTransformer[Int,Int] {
    override def transform(elem: Int): Int = elem*2
  }).toString)

  println(listOfIntegers.filter(new MyPredicate[Int] {
    override def test(elem: Int): Boolean = elem%2 == 0
  }).toString)

  val anotherListOfIntegers:MyGenericList[Int] = new ConsGeneric(4,new ConsGeneric(5,new ConsGeneric(6,EmptyGeneric)))

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new MyTransformer[Int,MyGenericList[Int]] {
    override def transform(elem: Int): MyGenericList[Int] = new ConsGeneric[Int](elem,new ConsGeneric[Int](elem+1,EmptyGeneric))
  }).toString)
}

