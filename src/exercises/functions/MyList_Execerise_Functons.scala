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
  // HOFS
  def foreach(f:A=>Unit):Unit
  def sort(compare:(A,A)=>Int):MyCaseGenericList[A]
  def zipWith[B,C](list:MyCaseGenericList[B],zip:(A,B)=>C):MyCaseGenericList[C]
  def fold[B](start:B)(operator:(B,A)=>B):B
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

  // HOFs
  override def foreach(f: Nothing => Unit): Unit = ()

  override def sort(compare: (Nothing, Nothing) => Int): MyCaseGenericList[Nothing] = EmptyCaseGeneric

  override def zipWith[B, C](list: MyCaseGenericList[B], zip: (Nothing, B) => C): MyCaseGenericList[C] = {
    if(!list.isEmpty) throw new RuntimeException("Lists do not hae same length")
    else EmptyCaseGeneric
  }

  override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
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

  //HOFs
  override def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare:(A,A)=>Int):MyCaseGenericList[A] = {
    def insert(x: A, value: MyCaseGenericList[A]):MyCaseGenericList[A] = {
      if(value.isEmpty) new ConsCaseGeneric(x,EmptyCaseGeneric)
      else if( compare(x,value.head)<=0) new ConsCaseGeneric(x,value)
      else new ConsCaseGeneric(value.head,insert(x,value.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h,sortedTail)
  }

  override def zipWith[B, C](list: MyCaseGenericList[B], zip: (A, B) => C): MyCaseGenericList[C] = {
    if(list.isEmpty) throw new RuntimeException("Lists do not hae same length")
    else new ConsCaseGeneric(zip(h,list.head),tail.zipWith(list.tail,zip))
  }

  /*
  * [1,2,3].fold(0)(+) =
  * [2,3].fold(1)(+) = [3].fold(3)(+) = [].fold(6)(+) = 6
  * */
  override def fold[B](start: B)(operator: (B, A) => B): B = {
    val newStart = operator(start,h)
    tail.fold(operator(start,h))(operator)
  }
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

  val anotherListOfIntegers:MyCaseGenericList[Int] = new ConsCaseGeneric(4,new ConsCaseGeneric(5,EmptyCaseGeneric))

  println(listOfIntegers ++ anotherListOfIntegers)

  println(listOfIntegers.flatMap(new Function1[Int,MyCaseGenericList[Int]] {
    override def apply(elem: Int): MyCaseGenericList[Int] = new ConsCaseGeneric[Int](elem,new ConsCaseGeneric[Int](elem+1,EmptyCaseGeneric))
  }).toString)

  println(listOfIntegers == clonelistOfIntegers)

  listOfIntegers.foreach(println)

  println(listOfIntegers.sort((x,y)=>y-x))

  println(anotherListOfIntegers.zipWith[String,String](listOfStrings,_+"-"+_))

  println(listOfIntegers.fold(0)(_+_))

  val combinations = for{
    n<- listOfIntegers
    s <- listOfStrings
  } yield n+"-"+s

  println(combinations)
}

