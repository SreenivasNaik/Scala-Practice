package exercises.functions



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


abstract class MyCaseGenericList[+A] {
  /*
  *  head = first element of the list
  * tail = last element of the list
  * isEmpty -> list is empty or not
  *  add(int) -> new list with this element added
  *  toString => a string representation of the list
  * */

  def head:A
  def tail:MyCaseGenericList[A]
  def isEmpty:Boolean
  def add[B>:A](element:B):MyCaseGenericList[B]
   def printElements:String

  override def toString: String = "["+printElements+"]"

  def map[B](transformer: A=>B):MyCaseGenericList[B]
  def flatMap[B](transformer: A=>MyCaseGenericList[B]):MyCaseGenericList[B]
  def filter(predicate: A=>Boolean):MyCaseGenericList[A]
  def ++[B >: A](list:MyCaseGenericList[B]):MyCaseGenericList[B]
}
case object EmptyCaseGeneric extends MyCaseGenericList[Nothing]{
  def head:Nothing = throw new NoSuchElementException
  def tail:MyCaseGenericList[Nothing] = throw new NoSuchElementException
  def isEmpty:Boolean = true
  def add[B >: Nothing](element:B):MyCaseGenericList[B] = new ConsCaseGeneric(element,EmptyCaseGeneric)

  override def printElements: String = ""

  def map[B](transformer: Nothing=>B ):MyCaseGenericList[B] = EmptyCaseGeneric
  def flatMap[B](transformer: Nothing=> MyCaseGenericList[B]):MyCaseGenericList[B] = EmptyCaseGeneric
  def filter(predicate: Nothing=>Boolean):MyCaseGenericList[Nothing] = EmptyCaseGeneric

  override def ++[B >: Nothing](list: MyCaseGenericList[B]): MyCaseGenericList[B] = list
}

case class ConsCaseGeneric[+A](h:A, t:MyCaseGenericList[A]) extends MyCaseGenericList[A] {
  def head: A = h

  def tail: MyCaseGenericList[A] = t

  def isEmpty: Boolean = false

  def add[B>:A](element: B): MyCaseGenericList[B] = new ConsCaseGeneric(element, this)

  override  def printElements: String =
    if (t.isEmpty) "" + h
    else h + " " + t.printElements

  override def filter(predicate: A=>Boolean): MyCaseGenericList[A] =
    if(predicate.apply(h)) new ConsCaseGeneric(h,t.filter(predicate))
    else t.filter(predicate)

  /*
  * [1,2,3].map(n*2)
  * */
  def map[B](transformer: A=>B):MyCaseGenericList[B] =
    new ConsCaseGeneric[B](transformer.apply(h),t.map(transformer))

  def flatMap[B](transformer: A=>MyCaseGenericList[B]):MyCaseGenericList[B] =
    transformer(h) ++ t.flatMap(transformer)

  override def ++[B >: A](list: MyCaseGenericList[B]): MyCaseGenericList[B] = new ConsCaseGeneric[B](h,t ++ list)
}

//trait MyPredicate[-T]{
//  def test(elem:T):Boolean
//}
//trait MyTransformer[-A,B]{
//  def transform(elem:A):B
//}

object CaseListTest2 extends App {

  val listOfIntegers:MyCaseGenericList[Int] = new ConsCaseGeneric(1,new ConsCaseGeneric(2,new ConsCaseGeneric(3,EmptyCaseGeneric)))
  val clonelistOfIntegers:MyCaseGenericList[Int] = new ConsCaseGeneric(1,new ConsCaseGeneric(2,new ConsCaseGeneric(3,EmptyCaseGeneric)))
  val listOfStrings:MyCaseGenericList[String] = new ConsCaseGeneric("Hello",new ConsCaseGeneric[String]("Scala",EmptyCaseGeneric))

 println(listOfIntegers.toString)
  println(listOfStrings.toString)

  println(listOfIntegers.map(new Function1[Int,Int] {
    override def apply(elem: Int): Int = elem*2
  }).toString)

  println(listOfIntegers.filter(new Function1[Int,Boolean] {
    override def apply(elem: Int): Boolean = elem%2 == 0
  }).toString)

  val anotherListOfIntegers:MyCaseGenericList[Int] = new ConsCaseGeneric(4,new ConsCaseGeneric(5,new ConsCaseGeneric(6,EmptyCaseGeneric)))

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new Function1[Int,MyCaseGenericList[Int]] {
    override def apply(elem: Int): MyCaseGenericList[Int] = new ConsCaseGeneric[Int](elem,new ConsCaseGeneric[Int](elem+1,EmptyCaseGeneric))
  }).toString)

  println(listOfIntegers == clonelistOfIntegers)
}

